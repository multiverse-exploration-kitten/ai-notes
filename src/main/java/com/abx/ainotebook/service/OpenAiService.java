package com.abx.ainotebook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.concurrent.ExecutorService;

public class OpenAiService {
    private static final String BASE_URL = "https://api.openai.com/";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final ObjectMapper mapper = defaultObjectMapper();

    private final OpenAiApi api;
    private final ExecutorService executorService;
}
