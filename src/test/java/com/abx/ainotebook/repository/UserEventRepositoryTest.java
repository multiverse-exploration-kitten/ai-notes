package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.UserEventMongoModel;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class UserEventRepositoryTest {
    @Autowired
    private UserEventRepository userEventRepository;

    private UUID noteId;
    private UUID userId;

    @BeforeEach
    void before() {
        noteId = UUID.randomUUID();
        userId = UUID.randomUUID();
    }

    @Test
    public void userEventRepository_Save_ReturnSavedUserEvent() {
        UserEventMongoModel model = new UserEventMongoModel();
        Map<String, Object> eventAttributes = new HashMap<>();
        eventAttributes.put("pressedKey", "K");
        model.setId(UUID.randomUUID());
        model.setUserId(userId);
        model.setNoteId(noteId);
        model.setEventType("Keystroke");
        model.setEventAttributes(eventAttributes);
        model.setTimestamp(System.currentTimeMillis());

        UserEventMongoModel savedUserEventMongoModel = userEventRepository.save(model);

        Assertions.assertThat(savedUserEventMongoModel).isNotNull();
    }
}
