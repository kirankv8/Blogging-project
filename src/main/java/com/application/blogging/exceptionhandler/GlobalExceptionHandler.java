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
	public ResponseEntity<?> resourceException(ResourceNotFoundException exception, WebRequest request) {
		ErrorResponse response = new ErrorResponse(exception.getMessage(), exception.getStatuscode());
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<Map<String, String>>
	 * handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
	 * Map<String, String> resp = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String message = error.getDefaultMessage();
	 * resp.put(fieldName, message); });
	 *
	 * return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	 * }
	 */
}
