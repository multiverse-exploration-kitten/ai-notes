package com.abx.ainotebook.configuration;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiServiceConfiguration {

    @Value("${openai.token}")
    private String openaiToken;

    @Value("${openai.token}")
    private String openaiToken2;

    @Bean
    @Qualifier("gpt-company-account-1")
    public OpenAiService openAiService() {
        return new OpenAiService(openaiToken);
    }

    @Bean
    @Qualifier("gpt-company-account-2")
    public OpenAiService openAiService2() {
        return new OpenAiService(openaiToken2);
    }
}
