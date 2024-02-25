package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.InsightService;
import com.abx.ainotebook.service.NoteService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GenInsightController.class)
class GenInsightControllerTest {

    public static final String NOTE_CONTROLLER_ROOT_PATH = "note";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsightService insightService;

    @MockBean
    private NoteService noteService;

    @Test
    void genInsight() throws Exception {
        UUID noteId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Note note = new Note();
        note.setContent("Chapter I");
        note.setId(noteId);
        note.setUserId(userId);
        Mockito.when(noteService.findById(noteId)).thenReturn(note);
        Mockito.when(insightService.genInsight(note.getContent())).thenReturn("test");
        mockMvc.perform(MockMvcRequestBuilders.get(
                        String.format("/" + NOTE_CONTROLLER_ROOT_PATH + "/%s/insight", noteId)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void genSummary() throws Exception {
        UUID noteId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Note note = new Note();
        note.setContent("Chapter I");
        note.setId(noteId);
        note.setUserId(userId);
        Mockito.when(noteService.findById(noteId)).thenReturn(note);
        Mockito.when(insightService.genInsight(note.getContent())).thenReturn("test");
        mockMvc.perform(MockMvcRequestBuilders.get(
                        String.format("/" + NOTE_CONTROLLER_ROOT_PATH + "/%s/summary", noteId)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
