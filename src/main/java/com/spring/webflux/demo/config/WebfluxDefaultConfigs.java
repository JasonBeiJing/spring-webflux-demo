package com.spring.webflux.demo.config;

import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebHandler;

@Component
public class WebfluxDefaultConfigs {

	@Autowired
	private List<ApplicationContext> acs;
	
	@Autowired(required = false)
	private List<HttpHandler> httpHandlers;
	
	@Autowired(required = false)
	private List<WebHandler> webHandlers;
	
	@Autowired(required = false)
	private List<WebClient> webClients;
	
	@PostConstruct
	public void printConfigs() {
		if (Objects.nonNull(acs)) {
			acs.forEach(ac -> System.err.println("ApplicationContext --> " + ac.getClass().getCanonicalName()));
		}
		if (Objects.nonNull(httpHandlers)) {					
			httpHandlers.forEach(httpHandler -> System.err.println("HttpHandler --> " + httpHandler.getClass().getCanonicalName()));
		}
		if (Objects.nonNull(webHandlers)) {			
			webHandlers.forEach(webHandler -> System.err.println("WebHandler --> " + webHandler.getClass().getCanonicalName()));
		}
		if (Objects.nonNull(webClients)) {			
			webClients.forEach(webClient -> System.err.println("WebClient --> " + webClient.getClass().getCanonicalName()));
		}
	}
}
