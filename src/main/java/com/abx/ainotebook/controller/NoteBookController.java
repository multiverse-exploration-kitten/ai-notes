package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableNotebookDto;
import com.abx.ainotebook.dto.NotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.NoteBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteBookController {
    private final NoteBookService noteBookService;

    public NoteBookController(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @GetMapping("/notebook/{userId}")
    public ResponseEntity<List<NotebookDto>> getNotebooksByUserId(@PathVariable UUID userId) {
        if (Objects.equals(userId, "") || Objects.isNull(userId)) {
            return ResponseEntity.badRequest().body(null);
        }
        List<Notebook> notebooks = noteBookService.findByUserId(userId);
        List<NotebookDto> notebookDtos = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            NotebookDto notebookDto = ImmutableNotebookDto.builder()
                    .category(notebook.getCategory())
                    .title(notebook.getTitle())
                    .createdAt(notebook.getCreatedAt())
                    .updatedAt(notebook.getUpdatedAt())
                    .build();
            notebookDtos.add(notebookDto);
        }
        return ResponseEntity.ok(notebookDtos);
    }

    @GetMapping("/notebook/{userId}/{category}")
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

    @PostMapping("/notebook/{user-uuid}/create")
    public ResponseEntity<NotebookDto> createNotebook(
            @RequestBody CreateNotebookDto createNotebookDto, @PathVariable("user-uuid") UUID userUuid) {
        if (Objects.equals(createNotebookDto.getTitle(), null)) {
            return ResponseEntity.badRequest().build();
        }
        if (Objects.equals(createNotebookDto.getTitle(), null)) {
            return ResponseEntity.badRequest().build();
        }
        Notebook createdNotebook = noteBookService.createNotebook(createNotebookDto, userUuid);
        NotebookDto responseNotebook = ImmutableNotebookDto.builder()
                .category(createdNotebook.getCategory())
                .title(createdNotebook.getTitle())
                .createdAt(createdNotebook.getCreatedAt())
                .updatedAt(createdNotebook.getUpdatedAt())
                .build();
        return ResponseEntity.ok(responseNotebook);
    }
}
