package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.UserEventMongoModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.HashMap;
import java.util.UUID;

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
    public void UserEventRepository_Save_ReturnSavedUserEvent() {
        UserEventMongoModel model = new UserEventMongoModel();
        HashMap<String, Object> eventAttributes = new HashMap<>();
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
