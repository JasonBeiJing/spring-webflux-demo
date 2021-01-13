package com.spring.webflux.demo.way2.handler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LocalDateTimeHandler {

	public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(LocalTime.now()),
				LocalTime.class);
	}

	public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(LocalDate.now()),
				LocalDate.class);
	}

	/**
	 * 服务推送: 短轮询：利用ajax定期向服务器请求，无论数据是否更新立马返回数据，高并发情况下可能会对服务器和带宽造成压力；
	 * 长轮询：利用comet不断向服务器发起请求，服务器将请求暂时挂起，直到有新的数据的时候才返回，相对短轮询减少了请求次数； SSE：服务端推送（Server
	 * Send Event），在客户端发起一次请求后会保持该连接，服务器端基于该连接持续向客户端发送数据，从HTML5开始加入。 web
	 * socket：这是也是一种保持连接的技术，并且是双向的，从HTML5开始加入，并非完全基于HTTP，适合于频繁和较大流量的双向通讯场景。
	 */

	// 既然响应式编程是一种基于数据流的编程范式，自然在服务器推送方面得心应手;如下,每秒推送一次数据给客户端(完全基于http协议)
	public Mono<ServerResponse> sendDateTimePerSec(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
				.body(Flux.interval(Duration.ofSeconds(1)).map(l -> LocalDateTime.now()), LocalDateTime.class);
	}
}
