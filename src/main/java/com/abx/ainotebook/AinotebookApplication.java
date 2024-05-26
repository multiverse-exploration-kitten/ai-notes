package com.abx.ainotebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class AinotebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(AinotebookApplication.class, args);
    }

    @GetMapping("/")
    public String healthCheck() {
        return "AI Notebook Service is running!";
    }
}
