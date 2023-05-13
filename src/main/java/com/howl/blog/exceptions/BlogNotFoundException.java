package com.howl.blog.exceptions;

public class BlogNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public BlogNotFoundException(String message) {
        super(message);
    }
}
