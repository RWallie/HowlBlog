package com.howl.blog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howl.blog.model.Blog;
import com.howl.blog.service.BlogService;


@RestController
@RequestMapping("/blogs")
public class BlogController {
  @Autowired
  private BlogService blogService;

  @GetMapping
  public ResponseEntity<List<Blog>> getAllBlogs() {
    return this.blogService.getAllBlogs();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
    return this.blogService.getBlogById(id);
  }

  @PostMapping
  public ResponseEntity<Blog> addBlogPost(@RequestBody Blog blog) {
    return this.blogService.addBlog(blog);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Blog> updateBlogPostById(@PathVariable Long id, @RequestBody Blog newBlogData) {
    return this.blogService.updateBlogPostById(id, newBlogData);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Blog> deleteBlogPostById(@PathVariable Long id) {
    return this.blogService.deleteBlogPostById(id);
  }

}
