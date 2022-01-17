package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
public class Controller {

  private final WebClient.Builder client;
  private final WebClient.Builder requestClient;

  public Controller(@Qualifier("load-balanced") Builder client,
                    @Qualifier("load-balanced-request-scope") Builder requestClient) {
    this.client = client;
    this.requestClient = requestClient;
  }

  private void execute() {
    try {
      client.baseUrl("http://demo")
            .build()
            .get()
            .uri("/hello")
            .retrieve()
            .bodyToMono(String.class)
            .block();

      log.info("success");
    } catch (Exception e) {
      log.error("failed", e);
    }
  }

  @GetMapping("my-health-check")
  public void health() {
  }

  @GetMapping("test-call-2")
  public void test2() {
    execute();
  }

  @GetMapping("test-call-3")
  public Mono<String> test3() {
    return requestClient
        .baseUrl("http://demo")
        .build()
        .get()
        .uri("/hello")
        .retrieve()
        .bodyToMono(String.class);
  }

  @GetMapping("test-call")
  public Mono<String> test() {
    return client
        .baseUrl("http://demo")
        .build()
        .get()
        .uri("/hello")
        .retrieve()
        .bodyToMono(String.class);
  }

  @GetMapping("hello")
  public String hello() {
    log.info("retrieved hello");
    return "hello world";
  }

}
