package com.abx.ainotebook.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class OpenAiResponse<T> {
    /**
     * A list containing the actual results
     */
    public List<T> data;

    /**
     * The type of object returned, should be "list"
     */
    public String content;

    /**
     * The first id included
     */
    @JsonProperty("user_id")
    public String userId;

    /**
     * The last id included
     */
    @JsonProperty("notebook_id")
    public String notebookId;
}
