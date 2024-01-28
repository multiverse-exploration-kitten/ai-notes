package com.abx.ainotebook.configuration;

import com.abx.ainotebook.generativeai.GenerativeAiService;
import com.abx.ainotebook.generativeai.GptService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiServiceConfiguration {

    @Value("${openai.token}")
    private String openaiToken;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openaiToken);
    }

    @Bean
    @Qualifier("openAiService")
    public GenerativeAiService<String, List<CompletionChoice>> gptService(OpenAiService openAiService) {
        return new GptService(openAiService);
    }
}
