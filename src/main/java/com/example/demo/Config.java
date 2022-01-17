package com.example.demo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableEurekaClient
public class Config {

  @Primary
  @Bean
  WebClient.Builder builderDefault() {
    return WebClient.builder();
  }

  @Bean("load-balanced")
  @LoadBalanced
  WebClient.Builder builder(WebClient.Builder builder) {
    return builder.clone();
  }

}