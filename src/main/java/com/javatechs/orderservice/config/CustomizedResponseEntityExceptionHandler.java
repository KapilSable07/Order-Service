package com.javatechs.orderservice.config;

import com.javatechs.orderservice.Error.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder().time(LocalDateTime.now()).errorMessage(ex.getMessage())
                .description(request.getDescription(false)).build();


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.builder().time(LocalDateTime.now()).errorMessage(ex.getMessage())
                .description(request.getDescription(false)).build();


        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
