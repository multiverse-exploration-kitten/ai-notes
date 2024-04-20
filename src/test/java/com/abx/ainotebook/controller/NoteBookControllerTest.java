package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableCreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableNotebookDto;
import com.abx.ainotebook.dto.NotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.JwtService;
import com.abx.ainotebook.service.NoteBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(NoteBookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NoteBookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private NoteBookService noteBookService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findByCategoryUAndUserId() throws Exception {
        UUID userID = UUID.randomUUID();
        Notebook newNotebook = new Notebook(userID, "", "History");
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(newNotebook);
        Mockito.when(noteBookService.findByCategoryAndUserId("History", userID))
                .thenReturn(notebooks);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notebook/%s/History", userID)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("History"));
    }

    @Test
    public void createNotebook() throws Exception {
        UUID userID = UUID.randomUUID();
        long timestamp = 02042024;
        NotebookDto notebookdto = ImmutableNotebookDto.builder()
                .userID(userID)
                .title("")
                .category("")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();
        CreateNotebookDto createNotebookDto =
                ImmutableCreateNotebookDto.builder().category("").title("").build();
        Notebook notebook = new Notebook(notebookdto.userID(), notebookdto.getTitle(), notebookdto.getCategory());

        Mockito.when(noteBookService.createNotebook(createNotebookDto, userID)).thenReturn(notebook);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notebook/%s/create", userID)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
