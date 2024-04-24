package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNotebookDto;
import com.abx.ainotebook.dto.ImmutableCreateNotebookDto;
import com.abx.ainotebook.model.Notebook;
import com.abx.ainotebook.service.JwtService;
import com.abx.ainotebook.service.NoteBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
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
    private static final String NOTEBOOK_API_BASE = "/notebook-api/user/";
    private static final String CATEGORY_HISTORY = "History";
    private static final String CATEGORY_SCIENCE = "Science";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private NoteBookService noteBookService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private UUID userId;
    private CreateNotebookDto createNotebookDto;
    private Notebook notebook;

    @BeforeEach
    public void setup() {
        userId = UUID.randomUUID();
        createNotebookDto = ImmutableCreateNotebookDto.builder()
                .category(CATEGORY_SCIENCE)
                .title("New Discoveries")
                .build();
        notebook = new Notebook();
        notebook.setUserId(userId);
        notebook.setNotebookId(UUID.randomUUID());
        notebook.setTitle("New Discoveries");
        notebook.setCategory(CATEGORY_HISTORY);
        notebook.setCreatedAt(System.currentTimeMillis());
        notebook.setUpdatedAt(System.currentTimeMillis());
    }

    @Test
    public void givenUserId_whenFindByCategory_thenReturnsOk() throws Exception {
        List<Notebook> notebooks = Collections.singletonList(notebook);
        Mockito.when(noteBookService.findByCategoryAndUserId(CATEGORY_HISTORY, userId))
                .thenReturn(notebooks);

        mockMvc.perform(MockMvcRequestBuilders.get(NOTEBOOK_API_BASE + userId + "/category/" + CATEGORY_HISTORY))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(notebooks)));
    }

    @Test
    public void givenNotebookDetails_whenCreateNotebook_thenReturnsOk() throws Exception {
        Mockito.when(noteBookService.createNotebook(
                        ArgumentMatchers.any(CreateNotebookDto.class), ArgumentMatchers.eq(userId)))
                .thenReturn(notebook);

        mockMvc.perform(MockMvcRequestBuilders.post(NOTEBOOK_API_BASE + userId + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNotebookDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
