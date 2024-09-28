package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.application.internal.outboundservice;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.interfaces.rest.resources.GeminiInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {
    @Bean
    public RestClient GeminiRestClient(@Value("${gemini.api.url}") String _baseUrl, @Value("${gemini.api.key}") String _apiKey) {

        return RestClient.builder()
                .baseUrl(_baseUrl)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean
    public GeminiInterface geminiInterface(@Qualifier("GeminiRestClient") RestClient _restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(_restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(GeminiInterface.class);
    }
}

