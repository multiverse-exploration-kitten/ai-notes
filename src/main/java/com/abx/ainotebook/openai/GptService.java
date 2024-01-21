package com.abx.ainotebook.openai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GptService {

    private static final String GPT_MODEL = "babbage-002";
    private final OpenAiService openAiService;
    private final OpenAiService openAiServic2;

    public GptService(
            @Qualifier("gpt-company-account-1")OpenAiService openAiService,
            @Qualifier("gpt-company-account-2")OpenAiService openAiServic2){
        this.openAiService = openAiService;
        this.openAiServic2 = openAiServic2;
    }


    public List<CompletionChoice> complete(String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(GPT_MODEL)
                .echo(true)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices();
    }

    public String parseGptResponse(CompletionChoice choice) {
        // TODO: parse logic
        return "";
    }
}
