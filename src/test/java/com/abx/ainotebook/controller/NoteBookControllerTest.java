package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableCreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableNotebookDto;
import com.abx.ainotebook.dto.NotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.NoteBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(NoteBookController.class)
public class NoteBookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteBookService noteBookService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findByCategoryUAndUserId() throws Exception {
        UUID userID = UUID.randomUUID();
        Notebook newNotebook = new Notebook(userID, "", "History");
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(newNotebook);
        Mockito.when(noteBookService.findByCategoryUAndUserId("History", userID))
                .thenReturn(notebooks);

        mockMvc.perform(MockMvcRequestBuilders.get("/notebook/{userID}/History"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("History"));
    }

    @Test
    public void createNotebook() throws Exception {
        UUID userID = UUID.randomUUID();
        NotebookDto notebook = ImmutableNotebookDto.builder().getuserID(userID).build();
        CreateNotebookDto createNotebookDto =
                ImmutableCreateNotebookDto.builder().build();
        Mockito.when(noteBookService.createNotebook(createNotebookDto, userID)).thenReturn((Notebook) notebook);

        mockMvc.perform(MockMvcRequestBuilders.get("notebook/userID/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
