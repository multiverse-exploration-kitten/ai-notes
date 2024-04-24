package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.NotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.NoteBookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notebook-api")
public class NoteBookController {
    private static final Logger log = LoggerFactory.getLogger(NoteBookController.class);
    private final NoteBookService noteBookService;

    public NoteBookController(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @GetMapping("/notebook/list")
    public ResponseEntity<List<NotebookDto>> listNotebooks() {
        List<Notebook> notebooks = noteBookService.findAll();
        List<NotebookDto> notebookDtos = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            NotebookDto notebookDto = noteBookService.convertNotebookToDto(notebook);
            notebookDtos.add(notebookDto);
        }
        return ResponseEntity.ok(notebookDtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotebookDto>> getNotebooksByUserId(@PathVariable UUID userId) {
        if (Objects.equals(userId, "") || Objects.isNull(userId)) {
            return ResponseEntity.badRequest().body(null);
        }
        List<Notebook> notebooks = noteBookService.findByUserId(userId);
        List<NotebookDto> notebookDtos = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            NotebookDto notebookDto = noteBookService.convertNotebookToDto(notebook);
            notebookDtos.add(notebookDto);
        }
        return ResponseEntity.ok(notebookDtos);
    }

    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<List<Notebook>> findByCategoryUAndUserId(
            @PathVariable("category") String category, @PathVariable("userId") UUID userID) {
        if (Objects.equals(category, "")) {
            return ResponseEntity.badRequest().body(null);
        }
        if (Objects.equals(userID, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        var found = noteBookService.findByCategoryAndUserId(category, userID);
        return ResponseEntity.ok(found);
    }

    @PostMapping("/user/{userId}/create")
    public ResponseEntity<NotebookDto> createNotebook(
            @RequestBody CreateNotebookDto createNotebookDto, @PathVariable("userId") UUID userId) throws Exception {
        if (Objects.equals(createNotebookDto.getTitle(), null)) {
            return ResponseEntity.badRequest().build();
        }
        Notebook createdNotebook = noteBookService.createNotebook(createNotebookDto, userId);
        NotebookDto responseNotebookDto = noteBookService.convertNotebookToDto(createdNotebook);
        return ResponseEntity.ok(responseNotebookDto);
    }
}
