package com.abx.ainotebook.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(InsightService.class)
public class InsightServiceTest {

    @MockBean
    private InsightService insightService;

    @Test
    void genInsight() {
        String sentence = insightService.genInsight("Mr and Mrs Dursley, of number four, Privet Drive,"
                + " were proud to say that they were perfectly normal, thank you very much.");
//        System.out.println("insight: " + sentence);
        verify(insightService, times(1))
                .genInsight("Mr and Mrs Dursley, of number four, Privet Drive,"
                        + " were proud to say that they were perfectly normal, thank you very much.");
    }

    @Test
    void genSummary() {
        String sentence = insightService.genSummary("Mr and Mrs Dursley, of number four, Privet Drive,"
                + " were proud to say that they were perfectly normal, thank you very much.");
//        System.out.println("summary: " + sentence);
        verify(insightService, times(1))
                .genSummary("Mr and Mrs Dursley, of number four, Privet Drive,"
                        + " were proud to say that they were perfectly normal, thank you very much.");
    }
}
