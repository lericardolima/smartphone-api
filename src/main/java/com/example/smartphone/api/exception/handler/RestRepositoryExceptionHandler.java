package com.example.smartphone.api.exception.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestRepositoryExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handle(ConstraintViolationException e) {

		if (e.getConstraintName().equals("code_unique_key")) {
			SmartphoneCustomError errorResponse = new SmartphoneCustomError("code",
					"Código de identificação já está sendo usado.", e.getCause().getLocalizedMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		}

		throw e;
	}
}
