package com.howl.blog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Encapsulates a collection of BlogDto objects
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponse {
    private List<BlogDto> blogs; 
}
