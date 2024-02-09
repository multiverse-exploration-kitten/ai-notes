package com.abx.ainotebook.generativeai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import org.springframework.stereotype.Service;


public class GptService implements GenerativeAiService<String, List<CompletionChoice>> {
    private static final String GPT_MODEL = "babbage-002";
    private final OpenAiService openAiService;

    public GptService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @Override
    public List<CompletionChoice> complete(String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(GPT_MODEL)
                .echo(true)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices();
    }

    @Override
    public String parseGptResponse(List<CompletionChoice> completionChoices) {
        StringBuilder ans = new StringBuilder();
        for (CompletionChoice choice : completionChoices) {
            ans.append(parseSingleGptResponse(choice));
        }
        return ans.toString();
    }

    private String parseSingleGptResponse(CompletionChoice choice) {

        return choice.getText();
    }
}
