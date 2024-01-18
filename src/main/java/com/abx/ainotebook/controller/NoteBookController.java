package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.CreateNotebookDto;
import com.abx.ainotebook.service.NoteBookService;
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

    @GetMapping("/notebook/{user_uuid}/{user_id_category_index}")
    public ResponseEntity<List<Notebook>> findByCategoryUAndUserId(
            @PathVariable("user_id_category_index") String category, @PathVariable("user_uuid") UUID userID) {
        if (Objects.equals(category, "")) {
            return ResponseEntity.badRequest().body(null);
        }
        if (Objects.equals(userID, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        var found = noteBookService.findByCategoryUAndUserId(category, userID);
        return ResponseEntity.ok(found);
    }

    @PostMapping("notebook/create")
    public ResponseEntity<CreateNotebookDto> createNotebook(@RequestBody CreateNotebookDto createNotebookDto) {
        if (Objects.equals(createNotebookDto.getNotebookId(), null)) {
            return ResponseEntity.badRequest().body(null);
        }
        Notebook createdNotebook = noteBookService.createNotebook(createNotebookDto);
        CreateNotebookDto responseDto = createNotebookDto.convertToDto(createdNotebook);
        return ResponseEntity.ok(responseDto);
    }
}
