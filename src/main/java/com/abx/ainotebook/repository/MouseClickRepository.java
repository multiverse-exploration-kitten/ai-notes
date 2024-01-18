package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.MouseClickMongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface MouseClickRepository extends MongoRepository<MouseClickMongoModel, UUID> {
}
