package com.abx.ainotebook;

import com.abx.ainotebook.repository.UserEventRepository;
import com.abx.ainotebook.service.KafkaConsumerService;
import com.abx.ainotebook.service.KafkaProducerService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(KafkaProducerService.class)
public class TrackingServiceTest {
//    @MockBean
//    private final UserEventRepository userEventRepository;


}
