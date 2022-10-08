package ru.the_boring_developers.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.the_boring_developers.common.entity.property.RestProperties;

public class RestTemplateHelper {
    public static RestTemplate build(RestProperties restProperties, ObjectMapper objectMapper, ClientHttpRequestFactory clientHttpRequestFactory) {
        //Конфиг соединения
        PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
        poolingConnManager.setMaxTotal(restProperties.getPoolSize());
        //http клиент с параметрами из конфигов
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder
                .setConnectionManager(poolingConnManager)
                .build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(client);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(restProperties.getConnectTimeout());
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(restProperties.getConnectRequestTimeout());

        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(restProperties.getBaseUrl());
        restTemplate.setUriTemplateHandler(factory);
        if (clientHttpRequestFactory != null) {
            restTemplate.setRequestFactory(clientHttpRequestFactory);
        }

        return restTemplate;
    }

    public static RestTemplate build(RestProperties restProperties, ObjectMapper objectMapper) {
        return build(restProperties, objectMapper, null);
    }
}
