package com.howl.blog.dto;

import lombok.Builder;
import lombok.Data;

/*
 * Data Transfer Object related to our blog.
 * Controls the data that I can transfer throughout
 * the code. I can limit certain Blog data that I want
 * going from service to service.
 */

@Data
@Builder
public class BlogDto {
    private long id;
    private String title;
    private String message;
}
