package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.dto.ImmutableCreateNoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.JwtService;
import com.abx.ainotebook.service.NoteBookService;
import com.abx.ainotebook.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private NoteBookService noteBookService;

    @InjectMocks
    private NoteController noteController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    private static Random random = new Random();

    private Note mockNote;
    private UUID noteId;
    private UUID userId;
    private UUID notebookId;

    @BeforeEach
    void before() {
        noteId = UUID.randomUUID();
        userId = UUID.randomUUID();
        notebookId = UUID.randomUUID();
        mockNote = new Note(
                noteId,
                userId,
                notebookId,
                "Test",
                "Hello world!",
                System.currentTimeMillis(),
                System.currentTimeMillis());
    }

    @Test
    void testGetNote() throws Exception {
        Mockito.when(noteService.findById(noteId)).thenReturn(mockNote);
        mockMvc.perform(MockMvcRequestBuilders.get("/note-api/note/" + noteId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetNotes() throws Exception {
        Mockito.when(noteService.findAllNotes()).thenReturn(Arrays.asList(mockNote));
        mockMvc.perform(MockMvcRequestBuilders.get("/note-api/note/list"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/note-api/note/" + noteId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetNotesByNotebookId() throws Exception {
        Mockito.when(noteService.findByNotebookId(notebookId)).thenReturn(Arrays.asList(mockNote));
        mockMvc.perform(MockMvcRequestBuilders.get("/note-api/notebook/" + notebookId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetNotesByUserId() throws Exception {
        Mockito.when(noteService.findByUserId(userId)).thenReturn(Arrays.asList(mockNote));
        mockMvc.perform(MockMvcRequestBuilders.get("/note-api/user/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateNote() throws Exception {
        // Prepare the input and output objects
        CreateNoteDto createNoteDto = ImmutableCreateNoteDto.builder()
                .title("Test Note Title")
                .content("Test Note Content")
                .build();
        long timestamp = 02042024;
        Note mockCreatedNote = new Note();
        mockCreatedNote.setTitle(createNoteDto.getTitle());
        mockCreatedNote.setContent(createNoteDto.getContent());
        mockCreatedNote.setCreatedAt(timestamp);
        mockCreatedNote.setUpdatedAt(timestamp);
        Mockito.when(noteService.createNote(
                        Mockito.any(CreateNoteDto.class), Mockito.eq(userId), Mockito.eq(notebookId)))
                .thenReturn(mockCreatedNote);
        mockMvc.perform(MockMvcRequestBuilders.post("/note-api/user/" + userId + "/notebook/" + notebookId + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNoteDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
