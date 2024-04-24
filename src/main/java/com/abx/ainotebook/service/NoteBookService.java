package com.abx.ainotebook.service;

import com.abx.ainotebook.controller.NoteController;
import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableNotebookDto;
import com.abx.ainotebook.dto.NotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.repository.NotebookRepository;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NoteBookService {
    private NotebookRepository notebookRepository;
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    public NoteBookService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public NotebookDto convertNotebookToDto(Notebook notebook) {
        NotebookDto notebookDto = ImmutableNotebookDto.builder()
                .category(notebook.getCategory())
                .title(notebook.getTitle())
                .createdAt(notebook.getCreatedAt())
                .updatedAt(notebook.getUpdatedAt())
                .userID(notebook.getUserId())
                .id(notebook.getNotebookId())
                .build();
        return notebookDto;
    }

    public Notebook createNotebook(CreateNotebookDto createNotebookDto, UUID userId) throws Exception {
        if (createNotebookDto == null || userId == null) {
            throw new Exception("CreateNoteDto or userId is null");
        }
        Notebook notebook = new Notebook(
                UUID.randomUUID(),
                userId,
                createNotebookDto.getTitle(),
                createNotebookDto.getCategory(),
                System.currentTimeMillis(),
                System.currentTimeMillis());
        notebookRepository.save(notebook);
        return notebook;
    }

    public List<Notebook> findAll() {
        return notebookRepository.findAll();
    }

    public List<Notebook> findByUserId(UUID userId) {
        return notebookRepository.findByUserId(userId);
    }

    public List<Notebook> findByCategoryAndUserId(String category, UUID userID) {
        return notebookRepository.findByCategoryAndUserId(category, userID);
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

    public List<Notebook> findByCreatedDateAfter(long createdAt) {
        return notebookRepository.findByCreatedAtAfter(createdAt);
    }

    private void log(String message) {
        //        System.out.println(message);
        logger.info(message);
    }
}
