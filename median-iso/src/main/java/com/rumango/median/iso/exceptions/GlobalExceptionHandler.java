package com.rumango.median.iso.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rumango.median.iso.dto.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MedianException.class)
	protected ResponseEntity<Object> handleConflict(MedianException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false),
//				HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(RuntimeException.class)
//	public final ResponseEntity<ErrorDetails> handleAllException(RuntimeException ex, WebRequest request) {
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(MedianException.class)
	public final ResponseEntity<ErrorDetails> handleNotFoundException(MedianException ex, WebRequest request) {
		ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		return new ResponseEntity<ErrorDetails>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}