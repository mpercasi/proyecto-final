package com.example.tarjetas.configuration;

import com.example.tarjetas.exceptions.DuplicatedException;
import com.example.tarjetas.exceptions.NoValidAccountException;
import com.example.tarjetas.exceptions.NonExistentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionBankHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NonExistentException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "Este usuario no tiene tarjetas asociadas", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DuplicatedException.class})
    protected ResponseEntity<Object> handleDuplicate(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "El usuario ya cuenta con una tajeta asociada", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NoValidAccountException.class})
    protected ResponseEntity handleNoValidAccount(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "Debe tener una cuenta corriente, para poder solicitar una tarjeta", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
