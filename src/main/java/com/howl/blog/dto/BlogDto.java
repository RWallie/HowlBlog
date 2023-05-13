package com.howl.blog.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogDto {
    private long id;
    private String title;
    private String author;
    private String category;
    private String text;
    private Date publishDate;
    private Time publishTime;
}
