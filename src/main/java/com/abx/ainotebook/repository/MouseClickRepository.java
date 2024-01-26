package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.MouseClickMongoModel;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MouseClickRepository extends MongoRepository<MouseClickMongoModel, UUID> {}
