package com.example.smartphone.api.exception.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestRepositoryExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handle(ConstraintViolationException e) {

		if (e.getConstraintName().equals("code_unique_key")) {
			SmartphoneCustomError errorResponse = new SmartphoneCustomError("code",
					getMessage("smartphone.code.validation.already.exists"), e.getCause().getLocalizedMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		}

		throw e;
	}

	private String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
}
