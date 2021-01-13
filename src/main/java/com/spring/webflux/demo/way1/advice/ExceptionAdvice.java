package com.spring.webflux.demo.way1.advice;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public SerializableException handleException(RuntimeException exception) {
		return new SerializableException("xxxx");
	}

	static class SerializableException implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8969001229510987919L;
		private String msg;

		public SerializableException() {
			super();
		}

		public SerializableException(String msg) {
			super();
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}
}
