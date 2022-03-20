package com.poc.AuthService.exception;

import org.springframework.stereotype.Component;

//@ResponseStatus(value=HttpStatus.NOT_FOUND)
@Component
public class UserAlreadyExistException extends RuntimeException {

	

    private static final long serialVersionUID = 1L;
    private String errorMessage;
    
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public UserAlreadyExistException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public UserAlreadyExistException() {
	}
	
    
	
	

}
