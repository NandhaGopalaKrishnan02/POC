package com.connectionService.advice;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@SuppressWarnings("unused")
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(SignatureVerificationException exception){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(TokenExpiredException exception){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleException(Exception exception){
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
