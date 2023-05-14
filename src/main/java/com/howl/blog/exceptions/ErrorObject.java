package com.howl.blog.exceptions;

import java.util.Date;

import lombok.Data;

/*
 * An error object to contain information about an
 * exception that occurs in our controllers.
 * Error Object set in: ./GlobalExceptionHandler
 */

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
