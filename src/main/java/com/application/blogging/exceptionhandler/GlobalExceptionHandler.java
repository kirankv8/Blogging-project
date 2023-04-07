package com.application.blogging.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?>resourceException(ResourceNotFoundException exception,WebRequest request){
		ErrorResponse response=new ErrorResponse(exception.getMessage(),exception.getStatuscode());
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
	}
}
