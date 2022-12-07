package com.transport.sabi.api.controllers;

import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResourceNotFoundException {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(Exception exception) {
        return new ResponseEntity<>("Bad Request", new HttpHeaders(), HttpStatus.BAD_REQUEST);
   }

}
