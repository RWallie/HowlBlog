package com.howl.blog.exceptions;

/*
 * BlogNotFoundException extends RuntimeException gives the ability to throw
 * the error when it occurs within the BlogServiceImplementation class
 */

public class BlogNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public BlogNotFoundException(String message) {
        super(message);
    }
}
