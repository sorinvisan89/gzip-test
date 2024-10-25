package com.test.gzip.demo.client;

import com.example.client.invoker.ApiException;
import com.example.client.model.ExampleRequest;
import com.example.client.model.ExampleResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestGeneratedRestEasyClient {

    public static void main(String[] args) throws ApiException {

        CustomRestEasyApiClient client = new CustomRestEasyApiClient();

        final ExampleRequest request = new ExampleRequest();
        request.setName("hello");

        final ExampleResponse response = client.executeRequest("gzip", request);

        log.info("Got response via generated rest easy {}", response);
    }
}
