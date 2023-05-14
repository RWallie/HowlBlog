package com.howl.blog.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howl.blog.dto.BlogDto;
import com.howl.blog.service.BlogService;

/*
 * Rest Controler that accepts and respondes to HTTP requests at the /blogs endpoint
 * The blogService interface is injected into the controller.
 * Different CRUD mappings route the requests through the blogService interface
 * to the blogServiceImplementation where middleware handles data to the H2 
 * database and back, sending a ResponseEntity with the response data and 
 * appropriate HTTPStatus.
 */

@RestController
@RequestMapping("/blogs")
public class BlogController {

  private BlogService blogService;

  public BlogController(BlogService blogService){
    this.blogService = blogService;
  }

  @GetMapping
  public ResponseEntity<List<BlogDto>> getAllBlogs() {
    return new ResponseEntity<List<BlogDto>>(blogService.getAllBlogs(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
    return ResponseEntity.ok(blogService.getBlogById(id));
  }

  @PostMapping
  public ResponseEntity<BlogDto> addBlogPost(@RequestBody BlogDto blogDto) {
    return new ResponseEntity<BlogDto>(blogService.addBlog(blogDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BlogDto> updateBlogPostById(@PathVariable Long id, @RequestBody BlogDto blogDto) {
    BlogDto response = blogService.updateBlogById(id, blogDto);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBlogPostById(@PathVariable Long id) {
    blogService.deleteBlogById(id);

    return new ResponseEntity<>("Blog Deleted", HttpStatus.OK);
  }

}
