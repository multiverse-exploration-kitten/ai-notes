package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.dto.ImmutableCreateNoteDto;
import com.abx.ainotebook.dto.ImmutableNoteDto;
import com.abx.ainotebook.dto.NoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.NoteBookService;
import com.abx.ainotebook.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private static Random random = new Random();

    private Note mockNote;
    private UUID noteId;
    private UUID userId;
    private UUID notebookId;

    @BeforeEach
    void setUp() {
        noteId = UUID.randomUUID();
        userId = UUID.randomUUID();
        notebookId = UUID.randomUUID();
    }

    @Test
    void testGetNote() throws Exception {
        when(noteService.findById(noteId)).thenReturn(mockNote);

        mockMvc.perform(get("/notes/" + noteId))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNotes() throws Exception {
        when(noteService.findAllNotes()).thenReturn(Arrays.asList(mockNote, mockNote));

        mockMvc.perform(get("/notes/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(delete("/notes/" + noteId))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNotesByNotebookId() throws Exception {
        when(noteService.findByNotebookId(notebookId)).thenReturn(Arrays.asList(mockNote));

        mockMvc.perform(get("/notebooks/" + notebookId))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNotesByUserId() throws Exception {
        when(noteService.findByUserId(userId)).thenReturn(Arrays.asList(mockNote));

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk());
    }

//    @Test
//    void testCreateNote() throws Exception {
//        // Assuming CreateNoteDto has a builder pattern
//        CreateNoteDto createNoteDto = ImmutableCreateNoteDto.builder()
//                .title("Hello Title")
//                .content("Hello content")
//                .build();
//
//        // Assuming NoteDto has a builder pattern
//        NoteDto noteDto = ImmutableNoteDto.builder()
//                .title("Sample Title")
//                .content("Sample content.")
//                .createdAt(System.currentTimeMillis())
//                .updatedAt(System.currentTimeMillis())
//                .build();
//
//        when(noteService.createNote(any(CreateNoteDto.class), any(UUID.class), any(UUID.class))).thenReturn(mockNote);
//        when(noteService.convertNoteToDto(any(Note.class))).thenReturn(noteDto);
//
//        String jsonContent = "{\"title\":\"Sample Note Title 2\",\"content\":\"This is the content of the note, again.\"}";
//
//        mockMvc.perform(post("/user/" + userId + "/notebook/" + notebookId + "/create_note")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonContent))
//                .andExpect(status().isOk());
//    }
}
