package com.abx.ainotebook.client;

import com.abx.ainotebook.api.OpenAiResponse;
import com.abx.ainotebook.api.edit.EditRequest;
import com.abx.ainotebook.api.edit.EditResult;
import com.abx.ainotebook.api.model.Model;
import io.reactivex.Single;

public class OpenAiApi {
    @GET("v1/models")
    Single<OpenAiResponse<Model>> listModels();

    @GET("/v1/models/{model_id}")
    Single<Model> getModel(@Path("model_id") String modelId);

    @POST("/v1/edits")
    Single<EditResult> createEdit(@Body EditRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/edits")
    Single<EditResult> createEdit(@Path("engine_id") String engineId, @Body EditRequest request);
}
