package com.abx.ainotebook.generativeai;

import com.theokanning.openai.completion.CompletionChoice;

public class GptService implements GenerativeAiService<String, CompletionChoice> {
    @Override
    public CompletionChoice complete(String service) {
        return null;
    }
}
