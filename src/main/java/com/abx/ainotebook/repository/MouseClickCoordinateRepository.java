package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.MouseclickCoordinateMongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface MouseClickCoordinateRepository extends MongoRepository<MouseclickCoordinateMongoModel, UUID> {
}
