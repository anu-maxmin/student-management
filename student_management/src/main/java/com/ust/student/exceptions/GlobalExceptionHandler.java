package com.ust.student.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ust.student.responses.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistingException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmailAlreadyExist(EmailAlreadyExistingException emailAlreadyExistingException){
		ApiResponse<Object> response =new ApiResponse<>("conflict", HttpStatus.CONFLICT.value(),emailAlreadyExistingException.getMessage() ,null);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmployeNotFound(StudentNotFoundException studentNotFoundException){
		ApiResponse<Object> response =new ApiResponse<>("conflict", HttpStatus.CONFLICT.value(),studentNotFoundException.getMessage() ,null);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(InvalidPhoneNumberException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmployeNotFound(InvalidPhoneNumberException invalidPhoneNumberException){
		ApiResponse<Object> response =new ApiResponse<>("conflict", HttpStatus.CONFLICT.value(),invalidPhoneNumberException.getMessage() ,null);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(InvalidDepartmentException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmployeNotFound(InvalidDepartmentException invalidDepartmentException){
		ApiResponse<Object> response =new ApiResponse<>("conflict", HttpStatus.CONFLICT.value(),invalidDepartmentException.getMessage() ,null);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception exception){
		ApiResponse<Object> response =new ApiResponse<>("Internal server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage() ,null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
