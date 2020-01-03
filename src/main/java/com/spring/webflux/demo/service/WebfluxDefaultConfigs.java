package com.spring.webflux.demo.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebHandler;

@Component
public class WebfluxDefaultConfigs {

	@Autowired
	private List<ApplicationContext> acs;
	
	@Autowired(required = false)
	private List<HttpHandler> httpHandlers;
	
	@Autowired(required = false)
	private List<WebHandler> webHandlers;
	
	
	
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
	}
}
