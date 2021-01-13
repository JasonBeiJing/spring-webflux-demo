package com.spring.webflux.demo.way2.web_client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientMain {

	public static void main(String[] args) {
		Mono<ClientResponse> response = WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/api/time")
				.accept(MediaType.APPLICATION_JSON)
				.exchange();
		System.err.println("===> " + response.block().bodyToMono(String.class).block());
	}
}
