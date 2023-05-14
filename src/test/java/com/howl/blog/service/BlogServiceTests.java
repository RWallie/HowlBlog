package com.howl.blog.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.howl.blog.dto.BlogDto;
import com.howl.blog.exceptions.BlogNotFoundException;
import com.howl.blog.model.Blog;
import com.howl.blog.repository.BlogRepository;
import com.howl.blog.service.Implementation.BlogServiceImplementation;

/*
 * Unit tests for BlogServiceImplementation:
 * Tests all methods return expected data and status codes
 * Tests:
 *  - addBlogPost
 *  - getBlogById
 *  - getAllBlogs
 *  - updateBlogPostById
 *  - deleteBlogPostById
 * 
 * Tests methods with invalid id for BlogNotFoundException
 * Tests:
 *  - getBlogById
 *  - updateBlogPostById
 *  - deleteBlogPostById
 */

@ExtendWith(MockitoExtension.class)
public class BlogServiceTests {
  
  @Mock
  private BlogRepository blogRepository;

  @InjectMocks
  private BlogServiceImplementation blogService;

  @Test
  public void addBlogServiceTest() {
    Blog blog = Blog.builder()
      .id(1L)
      .title("Rock Climbing Grip Strength")
      .message("Lorem ipsum dolor sit amet")
      .build();

    BlogDto blogDto = BlogDto.builder()
      .title("Rock Climbing Grip Strength")
      .message("Lorem ipsum dolor sit amet")
      .build();

    when(blogRepository.save(Mockito.any(Blog.class))).thenReturn(blog);

    BlogDto savedBlog = blogService.addBlog(blogDto);

    Assertions.assertThat(savedBlog).isNotNull();
  }

  @Test
  public void getAllBlogsServiceTest() {
      // Create a list of blogs
      List<Blog> blogs = new ArrayList<>();
      blogs.add(Blog.builder()
              .id(1L)
              .title("Rock Climbing Grip Strength")
              .message("Lorem ipsum dolor sit amet")
              .build());
      blogs.add(Blog.builder()
              .id(2L)
              .title("Hiking Trails")
              .message("Consectetur adipiscing elit")
              .build());

      // Mock the blogRepository.findAll() method to return the list of blogs
      when(blogRepository.findAll()).thenReturn(blogs);

      // Call the getAllBlogs() method
      List<BlogDto> allBlogs = blogService.getAllBlogs();

      // Verify the result
      Assertions.assertThat(allBlogs).isNotNull();
      Assertions.assertThat(allBlogs).hasSize(2);

      // Verify the properties of the first blog
      BlogDto firstBlog = allBlogs.get(0);
      Assertions.assertThat(firstBlog.getId()).isEqualTo(1L);
      Assertions.assertThat(firstBlog.getTitle()).isEqualTo("Rock Climbing Grip Strength");
      Assertions.assertThat(firstBlog.getMessage()).isEqualTo("Lorem ipsum dolor sit amet");

      // Verify the properties of the second blog
      BlogDto secondBlog = allBlogs.get(1);
      Assertions.assertThat(secondBlog.getId()).isEqualTo(2L);
      Assertions.assertThat(secondBlog.getTitle()).isEqualTo("Hiking Trails");
      Assertions.assertThat(secondBlog.getMessage()).isEqualTo("Consectetur adipiscing elit");
  }

  @Test
  public void getBlogByIdServiceTest() {
    Blog blog = Blog.builder()
      .id(1L)
      .title("Rock Climbing Grip Strength")
      .message("Lorem ipsum dolor sit amet")
      .build();

    when(blogRepository.findById(1L)).thenReturn(Optional.ofNullable(blog));

    BlogDto savedBlog = blogService.getBlogById(1L);

    Assertions.assertThat(savedBlog).isNotNull();
    Assertions.assertThat(savedBlog.getId()).isEqualTo(1L);
    Assertions.assertThat(savedBlog.getTitle()).isEqualTo("Rock Climbing Grip Strength");
    Assertions.assertThat(savedBlog.getMessage()).isEqualTo("Lorem ipsum dolor sit amet");
  }

  @Test
  public void getBlogById_BlogNotFoundException_Test() {
    // Mock the blogRepository.findById() method to return an empty optional
    when(blogRepository.findById(1L)).thenReturn(Optional.empty());

    // Call the getBlogById method and expect a BlogNotFoundException
    Assertions.assertThatThrownBy(() -> blogService.getBlogById(1L))
      .isInstanceOf(BlogNotFoundException.class);
  }

  @Test
  public void updateBlogByIdServiceTest() {
    Blog blog = Blog.builder()
      .id(1L)
      .title("Rock Climbing Grip Strength")
      .message("Lorem ipsum dolor sit amet")
      .build();

    BlogDto blogDto = BlogDto.builder()
      .id(2L)
      .title("Hiking Trails")
      .message("Consectetur adipiscing elit")
      .build();

      when(blogRepository.findById(1L)).thenReturn(Optional.ofNullable(blog));
      when(blogRepository.save(Mockito.any(Blog.class))).thenReturn(blog);

      BlogDto updatedBlog = blogService.updateBlogById(1L, blogDto);

      Assertions.assertThat(updatedBlog).isNotNull();
      Assertions.assertThat(updatedBlog.getId()).isEqualTo(1L);
      Assertions.assertThat(updatedBlog.getTitle()).isEqualTo("Hiking Trails");
      Assertions.assertThat(updatedBlog.getMessage()).isEqualTo("Consectetur adipiscing elit");
    }

  @Test
  public void updateBlogById_BlogNotFoundException_Test() {
    // Mock the blogRepository.findById() method to return an empty optional
    when(blogRepository.findById(1L)).thenReturn(Optional.empty());

    // Create a BlogDto object for the update
    BlogDto blogDto = BlogDto.builder()
      .id(1L)
      .title("Hiking Trails")
      .message("Consectetur adipiscing elit")
      .build();

    // Call the updateBlogById method and expect a BlogNotFoundException
    Assertions.assertThatThrownBy(() -> blogService.updateBlogById(1L, blogDto))
      .isInstanceOf(BlogNotFoundException.class);
  }

  @Test
  public void deleteBlogByIdServiceTest() {
    Blog blog = Blog.builder()
      .id(1L)
      .title("Rock Climbing Grip Strength")
      .message("Lorem ipsum dolor sit amet")
      .build();

    when(blogRepository.findById(1L)).thenReturn(Optional.ofNullable(blog));

    // delete is a void method so we have to use AssertAll to handle the void method
    assertAll(() -> blogService.deleteBlogById(1L));
  }

  @Test
  public void deleteBlogById_BlogNotFoundException_Test() {
      // Mock the blogRepository.findById() method to return an empty optional
      when(blogRepository.findById(1L)).thenReturn(Optional.empty());

      // Call the deleteBlogById method and expect a BlogNotFoundException
      Assertions.assertThatThrownBy(() -> blogService.deleteBlogById(1L))
          .isInstanceOf(BlogNotFoundException.class);
  }
}
