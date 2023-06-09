package com.howl.blog.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*
 * Global exception handling a blog id that is not found.
 * StatusCode - NOT_FOUND - 404
 * Also handles POST and PUT requests to the blog
 * with invalid data - BAD_REQUEST - 400
 * ControllerAdvice annotation allows it to handle exceptions across multiple controllers
 * A new ErrorObject is created to encapsulate error details
 * 
 */


@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ErrorObject> handleBlogNotFoundException(BlogNotFoundException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    } 

    @ExceptionHandler(BlogBadRequestException.class)
    public ResponseEntity<ErrorObject> handleBlogBadRequestException(BlogBadRequestException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
