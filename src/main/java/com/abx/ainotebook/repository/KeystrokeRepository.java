package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.KeystrokeMongoModel;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KeystrokeRepository extends MongoRepository<KeystrokeMongoModel, UUID> {}
