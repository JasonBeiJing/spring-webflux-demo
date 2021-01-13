package com.spring.webflux.demo.way1.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webflux.demo.way1.vo.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/webflux")
public class SpringWebfluxController {

	// Mono implements CorePublisher 属于事件发布者
	@GetMapping("/users/{id}") // 进入方法，就是同步了，非阻塞模型体现在这之前，即接收http请求然后分发的地方
	public Mono<User> findUserById(@PathVariable Long id, ServerHttpRequest request, ServerHttpResponse response) {
		if (id < 0) {
			throw new RuntimeException();
		}
		return Mono.just(getUsers().get(0));
	}

	// Flux implements CorePublisher 属于事件发布者
	@GetMapping("/users")
	public Flux<User> findUsers(@RequestParam(required = false) String name) {
		return Flux.fromIterable(getUsers());
	}

	private List<User> getUsers() {
		List<User> users = Arrays.asList(new User(1L, "Jack"), new User(2L, "Alex"));
		// do more business
		// ...
		return users;
	}
}
