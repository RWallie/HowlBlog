package com.howl.blog.exceptions;

/*
 * BlogNotFoundException extends RuntimeException gives the ability
 * to throw the error when a 404 not found occurs within the 
 * BlogServiceImplementation class
 * These values are set on the ErrorObject within
 * the GlobalExceptionHandler class
 */

public class BlogNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public BlogNotFoundException(String message) {
        super(message);
    }
}
