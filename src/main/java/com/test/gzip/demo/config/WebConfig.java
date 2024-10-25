package com.test.gzip.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<GzipRequestFilter> gzipRequestFilter() {
        FilterRegistrationBean<GzipRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GzipRequestFilter());
        registrationBean.addUrlPatterns("/example/*");
        return registrationBean;
    }
}