package com.abx.ainotebook.generativeai;

public interface GenerativeAiService<GenerativeAiPrompt, GenerativeAiResponse> {

    GenerativeAiResponse complete(GenerativeAiPrompt prompt);

    String parseGptResponse(GenerativeAiResponse response);
}
