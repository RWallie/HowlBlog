package com.howl.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.howl.blog.model.Blog;
import com.howl.blog.repository.BlogRepository;

@RestController
@RequestMapping("/blogs")
public class BlogController {
  private final BlogRepository blogRepository;

  public BlogController(final BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  @GetMapping
  public ResponseEntity<List<Blog>> getAllBlogs() {
    try {
      List<Blog> blogList = new ArrayList<>();
      this.blogRepository.findAll().forEach(blogList::add);

      if (blogList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Blog> getBookById(@PathVariable Long id) {
    try {
      Optional<Blog> optionalBlog = this.blogRepository.findById(id);

      if (optionalBlog.isPresent()) {
        return new ResponseEntity<Blog>(optionalBlog.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<Blog> addBlogPost(@RequestBody Blog blog) {
    try {
      Blog blogObject = blogRepository.save(blog);
      
      return new ResponseEntity<Blog>(blogObject, HttpStatus.CREATED);

     } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Blog> updateBlogPostById(@PathVariable Long id, @RequestBody Blog newBlogData) {
    try {
     
      Optional<Blog> optionalBlog = blogRepository.findById(id);

      if (optionalBlog.isPresent()) {

        Blog blogToUpdate = optionalBlog.get();

        if (newBlogData.getTitle() != null) {
          blogToUpdate.setTitle(newBlogData.getTitle());
        }
        
        if (newBlogData.getAuthor() != null) {
          blogToUpdate.setAuthor(newBlogData.getAuthor());
        }

        if (newBlogData.getCategory() != null) {
          blogToUpdate.setCategory(newBlogData.getCategory());
        }

        if (newBlogData.getText() != null) {
          blogToUpdate.setText(newBlogData.getText());
        }

        if (newBlogData.getPublishDate() != null) {
          blogToUpdate.setPublishDate(newBlogData.getPublishDate());
        }

        if (newBlogData.getPublishTime() != null) {
          blogToUpdate.setPublishTime(newBlogData.getPublishTime());
        }

        Blog updatedBlog = this.blogRepository.save(blogToUpdate);

        return new ResponseEntity<Blog>(updatedBlog, HttpStatus.OK);

      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Blog> deleteBlogPostById(@PathVariable Long id) {

    try {
      Optional<Blog> blogToDeleteOptional = this.blogRepository.findById(id);

      if (blogToDeleteOptional.isPresent()) {
        Blog blogToDelete = blogToDeleteOptional.get();
        this.blogRepository.delete(blogToDelete);

        return new ResponseEntity<Blog>(blogToDelete, HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
