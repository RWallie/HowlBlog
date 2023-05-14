package com.howl.blog.service;


import java.util.List;

import com.howl.blog.dto.BlogDto;

/*
 * Interface to act as middle man between the 
 * BlogController and BlogServiceImplementation classes
 */

public interface BlogService {
  BlogDto addBlog(BlogDto blogDto);

  List<BlogDto> getAllBlogs();

  BlogDto getBlogById(Long id);

  BlogDto updateBlogById(Long id, BlogDto blogDto);

  void deleteBlogById(Long id);
}