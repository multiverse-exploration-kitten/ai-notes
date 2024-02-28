package com.abx.ainotebook.generativeai;

import java.util.Map;

public interface GenerativeAiService<GenerativeAiPrompt, GenerativeAiResponse> {

    GenerativeAiResponse complete(GenerativeAiPrompt prompt);

    String parseGptResponse(GenerativeAiResponse response);

    Map<String, Object> generateEmbedding(GenerativeAiPrompt prompt);
}

