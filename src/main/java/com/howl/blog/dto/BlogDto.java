package com.howl.blog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogDto {
    private long id;
    private String title;
    private String message;
}
