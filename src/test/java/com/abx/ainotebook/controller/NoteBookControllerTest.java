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
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
        CreateNotebookDto createNotebookDto = ImmutableCreateNotebookDto.builder()
                .title("Test Title")
                .category("History")
                .build();
        Notebook newNotebook = noteBookService.createNotebook(createNotebookDto, userID);
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(newNotebook);
        Mockito.when(noteBookService.findByCategoryAndUserId("History", userID)).thenReturn(notebooks);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notebook-api/user/%s/category/History", userID)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/notebook-api/user/%s/category/History",
        // userID)))
        //                .andExpect(MockMvcResultMatchers.status().isOk())
        //                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("History"));
    }

    @Test
    public void createNotebook() throws Exception {
        UUID userId = UUID.randomUUID();
        CreateNotebookDto createNotebookDto = ImmutableCreateNotebookDto.builder()
                .category("Science")
                .title("New Discoveries")
                .build();
        NotebookDto notebookDto = ImmutableNotebookDto.builder()
                .category("Science")
                .title("New Discoveries")
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .userID(userId)
                .id(UUID.randomUUID())
                .build();

        Notebook notebook = new Notebook(); // Assuming you have a proper constructor or use a builder
        notebook.setCategory("Science");
        notebook.setTitle("New Discoveries");
        notebook.setCreatedAt(System.currentTimeMillis());
        notebook.setUpdatedAt(System.currentTimeMillis());
        notebook.setUserId(userId);
        notebook.setNotebookId(notebookDto.getId());

        Mockito.when(noteBookService.createNotebook(
                        ArgumentMatchers.any(CreateNotebookDto.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(notebook);

        mockMvc.perform(MockMvcRequestBuilders.post(String.format("/notebook-api/user/%s/create", userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNotebookDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
