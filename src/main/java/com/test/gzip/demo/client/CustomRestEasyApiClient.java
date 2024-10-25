package com.test.gzip.demo.client;

import com.example.client.api.DefaultApi;
import com.example.client.invoker.ApiClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.plugins.interceptors.AcceptEncodingGZIPFilter;
import org.jboss.resteasy.plugins.interceptors.GZIPDecodingInterceptor;
import org.jboss.resteasy.plugins.interceptors.GZIPEncodingInterceptor;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

public class CustomRestEasyApiClient extends DefaultApi {
    public CustomRestEasyApiClient() {
        final ApiClient client = getApiClient();
        final var httpClient = client.getHttpClient();
        if (httpClient instanceof ResteasyClient resteasyClient) {

            resteasyClient.register(AcceptEncodingGZIPFilter.class);
            resteasyClient.register(GZIPEncodingInterceptor.class);
            resteasyClient.register(GZIPDecodingInterceptor.class);
            resteasyClient.register(ResteasyJackson2Provider.class); // Register Jackson provider
        }

        getApiClient().setBasePath("http://localhost:8080");
    }
}
