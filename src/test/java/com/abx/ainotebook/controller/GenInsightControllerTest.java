package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.ImmutableNoteDto;
import com.abx.ainotebook.dto.NoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.InsightService;
import com.abx.ainotebook.service.NoteService;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(GenInsightController.class)
class GenInsightControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Note mockNote;
    private UUID noteId;
    private UUID userId;
    private UUID notebookId;

    @MockBean
    private InsightService insightService;

    @MockBean
    private NoteService noteService;
    @InjectMocks
    private GenInsightController genInsightController;
    @Test
    void genInsight() throws Exception {
        UUID noteId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Note note = new Note(noteId, userId, "Chapter I");
        Mockito.when(noteService.findById(noteId)).thenReturn(note);
        Mockito.when(insightService.genInsight(note.getContent())).thenReturn("test");
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notes/%s/genIngsight", noteId))).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void genSummary() throws Exception {
        UUID noteId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Note note = new Note(noteId, userId, "Chapter I");
        Mockito.when(noteService.findById(noteId)).thenReturn(note);
        Mockito.when(insightService.genInsight(note.getContent())).thenReturn("test");
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notes/%s/genSummary", noteId))).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
