package com.spring.webflux.demo.way2.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.webflux.demo.way2.handler.LocalDateTimeHandler;

import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

	@Autowired
	private LocalDateTimeHandler handler;

	// router(path) -> handler(controller)
	@Bean
	public RouterFunction<ServerResponse> timerRouter() {
		return RouterFunctions
				.route(RequestPredicates.GET("/api/time"), new HandlerFunction<ServerResponse>() {
					@Override
					public Mono<ServerResponse> handle(ServerRequest request) {
						return handler.getTime(request);
					}
				})
				.andRoute(RequestPredicates.GET("/api/date"), request -> handler.getDate(request))
				.andRoute(RequestPredicates.GET("/api/date-times"), handler::sendDateTimePerSec);
	}
}
