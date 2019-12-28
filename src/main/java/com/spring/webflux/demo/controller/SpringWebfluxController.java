package com.spring.webflux.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webflux.demo.vo.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/webflux")
public class SpringWebfluxController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello, spring webflux.";
	}
	
	@GetMapping("/users/{id}")
	public Mono<User> findUserById(@PathVariable Long id) {
		User user = new User();
		user.setId(id);
		user.setName("xxx");
		return Mono.just(user);
	}
	
	@GetMapping("/users")
	public Flux<User> findUsers(@RequestParam String name) {
		User[] users = {new User(1L, "Jack"), new User(2L, "Alex")};
		return Flux.just(users);
	}
}
