package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.ImmutableUserEventDto;
import com.abx.ainotebook.dto.UserEventDto;
import com.abx.ainotebook.model.ImmutableKeystroke;
import com.abx.ainotebook.model.ImmutableMouseClick;
import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.service.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(TrackingController.class)
public class TrackingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserEventDto mouseclickUserEventDto;

    private UserEventDto keystrokeUserEventDto;

    private UUID userId;

    private UUID noteId;

    @BeforeEach
    void before() {
        userId = UUID.randomUUID();
        noteId = UUID.randomUUID();

        Map<String, Object> mouseClickAttributes = new HashMap<>();
        mouseClickAttributes.put("x", 16);
        mouseClickAttributes.put("y", 15);
        mouseClickAttributes.put("clickedTarget", "GenInsight");

        mouseclickUserEventDto = ImmutableUserEventDto.builder()
                .userId(userId)
                .noteId(noteId)
                .eventType("MouseClick")
                .eventAttributes(mouseClickAttributes)
                .build();

        Map<String, Object> keystrokeAttributes = new HashMap<>();
        keystrokeAttributes.put("pressedKey", "K");

        keystrokeUserEventDto = ImmutableUserEventDto.builder()
                .userId(userId)
                .noteId(noteId)
                .eventType("Keystroke")
                .eventAttributes(keystrokeAttributes)
                .build();
    }

    @Test
    public void trackingController_TrackMouseclick_ReturnsProducedUserEventDto() throws Exception {
        MouseClick mouseClick = ImmutableMouseClick.builder()
                .x(16)
                .y(15)
                .clickedTarget("GenInsight")
                .build();
        String jsonMouseClick = objectMapper.writeValueAsString(mouseClick);

        String url = String.format("/track-mouse-click/%s/%s", userId, noteId);
        Mockito.when(kafkaProducerService.recordMouseClick(userId, noteId, mouseClick))
                .thenReturn(Optional.of(mouseclickUserEventDto));
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMouseClick))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(userId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteId").value(noteId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventType").value("MouseClick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventAttributes.x").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventAttributes.y").value(15));
    }

    @Test
    public void trackingController_TrackKeystroke_ReturnsProducedUserEventDto() throws Exception {
        String pressedKey = "K";
        Keystroke keystroke =
                ImmutableKeystroke.builder().pressedKey(pressedKey).build();
        String jsonKeystroke = objectMapper.writeValueAsString(keystroke);

        String url = String.format("/track-keystroke/%s/%s", userId, noteId);
        Mockito.when(kafkaProducerService.recordKeystroke(userId, noteId, keystroke))
                .thenReturn(Optional.of(keystrokeUserEventDto));
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonKeystroke))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(userId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteId").value(noteId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventType").value("Keystroke"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventAttributes.pressedKey")
                        .value(pressedKey));
    }
}
