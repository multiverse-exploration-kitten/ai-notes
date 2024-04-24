package com.abx.ainotebook.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableCreateNotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.repository.NotebookRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(NoteBookService.class)
class NoteBookServiceTest {

    @MockBean
    private NotebookRepository notebookRepository;

    @MockBean
    private NoteBookService noteBookService;

    @MockBean
    private JwtService jwtService;

    @Test
    void createNotebook() throws Exception {
        UUID userID = UUID.randomUUID();

        CreateNotebookDto createNotebookDto =
                ImmutableCreateNotebookDto.builder().category("").title("").build();
        Notebook notebook = noteBookService.createNotebook(createNotebookDto, userID);

        Mockito.when(notebookRepository.save(notebook)).thenReturn(notebook);
        verify(noteBookService, times(1)).createNotebook(createNotebookDto, userID);
    }

    @Test
    void findAll() {
        noteBookService.findAll();
        verify(noteBookService, times((1))).findAll();
    }

    @Test
    void findByCategoryUAndUserId() {
        UUID userID = UUID.randomUUID();
        noteBookService.findByCategoryAndUserId("History", userID);
        verify(noteBookService, times((1))).findByCategoryAndUserId("History", userID);
    }

    @Test
    void findByTitleAndUserId() {
        UUID userID = UUID.randomUUID();
        noteBookService.findByCategoryAndUserId("Lesson One", userID);
        verify(noteBookService, times((1))).findByCategoryAndUserId("Lesson One", userID);
    }

    @Test
    void save() throws Exception {
        UUID userID = UUID.randomUUID();
        long timestamp = 02042024;
        CreateNotebookDto createNotebookdto =
                ImmutableCreateNotebookDto.builder().title("").category("").build();
        Notebook notebook = noteBookService.createNotebook(createNotebookdto, userID);
        noteBookService.save(notebook);
        verify(noteBookService, times(1)).save(notebook);
    }

    @Test
    void deleteById() {
        UUID notebookId = UUID.randomUUID();
        noteBookService.deleteById(notebookId);
        verify(noteBookService, times(1)).deleteById(notebookId);
    }

    @Test
    void findByCreatedDateAfter() {
        long timestamp = 02042024;
        noteBookService.findByCreatedDateAfter(timestamp);
        verify(noteBookService, times(1)).findByCreatedDateAfter(timestamp);
    }
}
