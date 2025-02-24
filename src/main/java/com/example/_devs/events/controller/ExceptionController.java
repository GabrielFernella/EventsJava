package com.example._devs.events.controller;

import com.example._devs.events.exception.EventNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<?> handleExceptionInternal(final RuntimeException anException, final Object body, final HttpServletRequest aRequest) {
        final var aMessage = "Error: " + anException.getMessage();
        return ResponseEntity.badRequest().body(aMessage);
    }

    @ExceptionHandler(EventNotFoundException.class)
    protected ResponseEntity<?> handleResourceNotFoundException(final RuntimeException anException, final Object body, final HttpServletRequest aRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(anException.getMessage());
    }

//    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
//    @Override
//    protected ResponseEntity<String> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//    }
}
