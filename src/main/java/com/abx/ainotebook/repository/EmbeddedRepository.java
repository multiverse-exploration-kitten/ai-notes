package com.abx.ainotebook.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.abx.ainotebook.model.EmbeddedModel;



@Repository
public interface EmbeddedRepository extends MongoRepository<EmbeddedModel, UUID> {

  List<EmbeddedModel> findByReferenceId(UUID referenceId);

  List<EmbeddedModel> findByType(String type);

  List<EmbeddedModel> findByCreatedAtAfter(Long createdAt);

  List<EmbeddedModel> findByReferenceIdAndType(UUID referenceId, String type);
  List<EmbeddedModel> findByReferenceIdAndIndex(UUID referenceId, Integer index);

}
