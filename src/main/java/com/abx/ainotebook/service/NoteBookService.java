package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.model.NotebookRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class NoteBookService {
    private NotebookRepository notebookRepository;

    public NoteBookService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public Notebook createNotebook(CreateNotebookDto createNotebookDto, UUID userUuid) {
        var notebook = new Notebook(userUuid, createNotebookDto.getTitle(), createNotebookDto.getCategory());
        notebookRepository.save(notebook);
        return notebook;
    }

    public List<Notebook> findAll() {
        return notebookRepository.findAll();
    }

    public List<Notebook> findByCategoryUAndUserId(String category, UUID userID) {
        return notebookRepository.findByCategoryUAndUserId(category, userID);
    }

    public List<Notebook> findByTitleAndUserId(String title, UUID userID) {
        return notebookRepository.findByTitleAndUserId(title, userID);
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void deleteById(UUID notebookId) {
        notebookRepository.deleteById(notebookId);
    }

    public List<Notebook> findByCreatedDateAfter(Timestamp createdAt) {
        return notebookRepository.findByCreatedAtAfter(createdAt);
    }
}
