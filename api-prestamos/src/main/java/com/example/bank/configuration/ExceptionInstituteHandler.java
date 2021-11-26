package com.example.bank.configuration;

import com.example.bank.exceptions.DuplicatedException;
import com.example.bank.exceptions.NonExistentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInstituteHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NonExistentException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "Item no encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DuplicatedException.class})
    protected ResponseEntity<Object> handleDuplicate(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "Item ya existente", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
