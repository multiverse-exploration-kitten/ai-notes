package com.abx.ainotebook.generativeai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import com.theokanning.openai.embedding.Embedding;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.embedding.EmbeddingResult;
import com.theokanning.openai.service.OpenAiService;
import com.abx.ainotebook.model.EmbeddedModel;
import com.abx.ainotebook.repository.EmbeddedRepository;

@Service
public class EmbeddingService {

  private final OpenAiService openAiService;
  private final EmbeddedRepository embeddedRepository;

  public EmbeddingService(OpenAiService openAiService, EmbeddedRepository embeddedRepository) {
    this.openAiService = openAiService;
    this.embeddedRepository = embeddedRepository;
  }

  public List<EmbeddedModel> createAndSaveEmbeddings(List<String> texts, UUID referenceId, String type) {
    List<EmbeddedModel> savedModels = new ArrayList<>();
    List<Embedding> embeddings = generateEmbeddings(texts);

    for (int i = 0; i < embeddings.size(); i++) {
      Embedding embedding = embeddings.get(i);
      Map<String, Float> embeddingMap = IntStream.range(0, embedding.getEmbedding().size())
          .boxed()
          .collect(Collectors.toMap(
              index -> index.toString(),
              index -> embedding.getEmbedding().get(index).floatValue()
          ));

      EmbeddedModel embeddedModel = new EmbeddedModel(
          UUID.randomUUID(),
          referenceId,
          type,
          embeddingMap,
          System.currentTimeMillis(),
          i
      );

      savedModels.add(embeddedRepository.save(embeddedModel));
    }
    return savedModels;
  }

  private List<Embedding> generateEmbeddings(List<String> texts) {
    EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
        .input(texts)
        .model("text-similarity-babbage-001")
        .build();

    EmbeddingResult embeddingResponse = openAiService.createEmbeddings(embeddingRequest);
    return embeddingResponse.getData();
  }
  public List<EmbeddedModel> findByReferenceIdAndIndex(UUID referenceId, Integer index) {
    return embeddedRepository.findByReferenceIdAndIndex(referenceId, index);
  }

  public List<EmbeddedModel> findByReferenceIdAndType(UUID referenceId, String type){
    return embeddedRepository.findByReferenceIdAndType(referenceId, type);
  }

  public List<EmbeddedModel> findByCreatedAtAfter(Long createdAt) {
    return embeddedRepository.findByCreatedAtAfter(createdAt);
  }

  public List<EmbeddedModel> findByType(String type) {
    return embeddedRepository.findByType(type);
  }

  public List<EmbeddedModel> findByReferenceId(UUID referenceId) {
    return embeddedRepository.findByReferenceId(referenceId);
  }
}

