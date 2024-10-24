package com.test.gzip.demo.controller;

import com.example.model.ExampleRequest;
import com.example.model.ExampleResponse;
import com.test.api.ExampleApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ExampleController implements ExampleApi {

    @Override
    public ResponseEntity<ExampleResponse> executeRequest(final ExampleRequest exampleRequest) {
        final ExampleResponse exampleResponse = ExampleResponse.builder()
                .result("OK")
                .build();

        log.info("Received request!");

        return ResponseEntity.ok(exampleResponse);
    }
}