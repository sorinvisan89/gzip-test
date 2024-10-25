package com.test.gzip.demo.client;

import com.example.client.invoker.ApiException;
import com.example.client.model.ExampleRequest;
import com.example.client.model.ExampleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class TestRestTemplateClient {

    public static void main(String[] args) throws IOException {

        final ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        final ExampleRequest request = new ExampleRequest();
        request.setName("hello");

        final ObjectMapper objectMapper = new ObjectMapper();

        final String jsonData = objectMapper.writeValueAsString(request);

        final byte[] compressedData = gzipCompress(jsonData);

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Encoding", "gzip");
        headers.add("Content-Type", "application/json");

        final HttpEntity<byte[]> requestEntity = new HttpEntity<>(compressedData, headers);

        final String url = "http://localhost:8080/example";
        final ResponseEntity<ExampleResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, ExampleResponse.class);

        log.info("Got response via rest template {}", response);

        log.info("Got response via rest template {}", response);


        log.info("Got response via rest template {}", response);
    }


    private static byte[] gzipCompress(final String data) throws IOException {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(data.getBytes());
            gzipStream.finish();
            return byteStream.toByteArray();
        }
    }
}
