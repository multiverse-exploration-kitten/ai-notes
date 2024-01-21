package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableNotebookDto;
import com.abx.ainotebook.dto.NotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.NoteBookService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("notebook/{user-uuid}/create")
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
