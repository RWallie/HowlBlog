package com.howl.blog.exceptions;

/*
 * BlogBadRequestException extends RuntimeException gives the ability
 * to throw the error when a 400 bad request is thrown within the 
 * BlogServiceImplementation class
 */

public class BlogBadRequestException extends RuntimeException{
  private static final long serialVersionUID = 1;

  public BlogBadRequestException(String message) {
      super(message);
  }
}
