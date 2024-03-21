package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.UserEventMongoModel;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEventRepository extends MongoRepository<UserEventMongoModel, UUID> {}
