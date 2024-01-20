package com.abx.ainotebook.model;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {
    @Id
    private UUID id;

    private String title;
    private String content;
    private String userId;
}
