package com.abx.ainotebook.controller;

import com.abx.ainotebook.service.InsightService;
import com.abx.ainotebook.service.NoteService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(GenInsightController.class)
class GenInsightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsightService insightService;

    @MockBean
    private NoteService noteService;

    @Test
    void genInsight() throws Exception {
        UUID noteId = UUID.randomUUID();
        String notes = "test";

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notes/%s/genIngsight", noteId)));
    }

    @Test
    void genSummary() throws Exception {
        UUID noteId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notes/%s/genSummary", noteId)));
    }
}
