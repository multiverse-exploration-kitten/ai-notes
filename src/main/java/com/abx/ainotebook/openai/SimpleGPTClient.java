package com.abx.ainotebook.openai;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleGPTClient {

    public SimpleGPTClient(){
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(API_KEY, Duration.ofSeconds(TIMEOUT_SECONDS))
                .newBuilder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST, PORT)))
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        this.openAiService = new OpenAiService(api, client.dispatcher().executorService());
    }
}
