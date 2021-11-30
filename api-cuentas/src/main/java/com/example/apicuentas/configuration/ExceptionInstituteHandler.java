package com.example.apicuentas.configuration;

import com.example.apicuentas.exceptions.DuplicatedException;
import com.example.apicuentas.exceptions.NonExistentCustomerPasswordException;
import com.example.apicuentas.exceptions.NonExistentException;
import com.example.apicuentas.exceptions.NonExisteteTarjetaDebito;
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

    @ExceptionHandler({NonExisteteTarjetaDebito.class})
    protected ResponseEntity<Object> handleNotCard(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, "No cuentas con esta Tarjeta", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({NonExistentCustomerPasswordException.class})
    protected ResponseEntity<Object> handleNotContra(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, "La contraseña es incorrecta", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
