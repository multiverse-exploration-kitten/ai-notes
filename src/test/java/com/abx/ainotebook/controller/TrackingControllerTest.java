package com.abx.ainotebook.controller;

import com.abx.ainotebook.service.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TrackingController.class)
public class TrackingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @BeforeEach
    void setup() {

    }

}
