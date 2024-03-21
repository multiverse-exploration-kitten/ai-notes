package com.abx.ainotebook.service;

import com.abx.ainotebook.generativeai.GenerativeAiService;
import com.theokanning.openai.completion.CompletionChoice;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InsightService {
    private final GenerativeAiService<String, List<CompletionChoice>> gptService;
    private final String insightPrompt = "Please generate an insight for the following note: ";
    private final String summaryPrompt =
            "Could you please generate a TLDR (Too Long Didn't Read) summary of the following notes? ";

    private String genInsightPrompt(String notes) {
        return String.format("%s %s", insightPrompt, notes);
    }

    private String genSummaryPrompt(String notes) {
        return String.format("%s %s", summaryPrompt, notes);
    }

    public InsightService(GenerativeAiService<String, List<CompletionChoice>> gptService) {
        this.gptService = gptService;
    }

    public String genInsight(String notes) {
        List<CompletionChoice> results = gptService.complete(genInsightPrompt(notes));
        return gptService.parseGptResponse(results);
    }

    public String genSummary(String notes) {
        List<CompletionChoice> results = gptService.complete(genSummaryPrompt(notes));
        return gptService.parseGptResponse(results);
    }
}
