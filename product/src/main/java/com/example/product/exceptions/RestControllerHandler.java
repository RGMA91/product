package com.example.product.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Hidden
@ControllerAdvice
public class RestControllerHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<Object> handleSortByPropertyException(PropertyReferenceException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
