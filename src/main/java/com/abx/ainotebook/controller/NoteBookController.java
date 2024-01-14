package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.Notebook;
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

    @GetMapping("/notebook/{user_id_category_index}")
    public List<Notebook> findByCategoryUAndUserId(@PathVariable String category, UUID userID) {
        return noteBookService.findByCategoryUAndUserId(category, userID);
    }

    @PostMapping("notebook/create")
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook) {
        if (Objects.equals(notebook.getNotebookId(), null)) {
            return ResponseEntity.badRequest().body(null);
        }

        var createdNotebook = noteBookService.createNotebook(
                notebook.getNotebookId(),
                notebook.getUserId(),
                notebook.getTitle(),
                notebook.getCategory(),
                notebook.getCreatedAt(),
                notebook.getUpdatedAt());
        return ResponseEntity.ok(createdNotebook);
    }
}
