package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.outboundservices;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    private static final String API_KEY = "TU_API_KEY_AQUI";
    private static final String OPENAI_URL = "https://api.openai.com/v1/completions";

    public String sendPrompt(String prompt) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(OPENAI_URL);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 1000);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(requestBody);
        post.setEntity(new StringEntity(jsonBody));

        var response = client.execute(post);
        var responseBody = EntityUtils.toString(response.getEntity());
        client.close();

        Map<String, Object> result = mapper.readValue(responseBody, Map.class);
        return ((Map<String, Object>) ((List<?>) result.get("choices")).get(0)).get("text").toString();
    }
}