package com.contact.smartcontactmanager.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException exception) {
		Map<String, String> errors = new LinkedHashMap<>();

		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		constraintViolations.forEach(constraintViolation -> {

			String nameProperty = constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessage();
			errors.put(nameProperty, message);

		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
}
