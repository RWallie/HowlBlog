package com.howl.blog.service.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.howl.blog.dto.BlogDto;
import com.howl.blog.exceptions.BlogBadRequestException;
import com.howl.blog.exceptions.BlogNotFoundException;
import com.howl.blog.model.Blog;
import com.howl.blog.repository.BlogRepository;
import com.howl.blog.service.BlogService;

/*
 * Implementation of BlogService.
 * Provides logic between the BlogController and BlogRepository
 * Performs operations for each controller mapping:
 * addBlog        - POST
 * getBlogById    - GET
 * getAllBlogs    - GET
 * updateBlogById - PUT
 * deleteBlogById - DELETE
 * Utilizes BlogRepository for database operations and converts response
 * data to Data Transfer Objects before returning back
 * Throws exceptions for BadRequests - Invalid data types for 
 * title and message properties on blogDto: 400 Status Code
 * Throws exceptions for ids NotFound - Ids that don't 
 * exists inside of the Blog Entity: 404 Status Code
 */

@Service
public class BlogServiceImplementation implements BlogService {

    
    private BlogRepository blogRepository;

    public BlogServiceImplementation(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) {
        System.out.println(blogDto.getTitle().getClass());
        System.out.println(blogDto.getMessage().getClass());

        // verifys the title and message types are not null and are not
        // empty strings before inserting into Blog entity
        if (blogDto.getTitle() == null || blogDto.getTitle().isEmpty()) {
            throw new BlogBadRequestException("Invalid Title: Title cannot be null or empty");
        }

        if (blogDto.getMessage() == null || blogDto.getMessage().isEmpty()) {
            throw new BlogBadRequestException("Invalid Message: Message cannot be null or empty");
        }

        Blog blog = mapToBlog(blogDto);

        Blog newBlog = blogRepository.save(blog);
        
        BlogDto blogResponse = mapToBlogDto(newBlog);

        return blogResponse;
    }   

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        //  convert blogs to stream to use map and transform each blog to a BlogDto
        // and collect and add back into a list to be returned
        return blogs.stream().map(b -> mapToBlogDto(b)).collect(Collectors.toList());
    }

    @Override
    public BlogDto getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog could not be found"));
        return mapToBlogDto(blog);
    }   

    @Override
    public BlogDto updateBlogById(Long id, BlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog could not be updated"));

        // Checks that title is not null - null will not throw an exception as you 
        // should be able to only update specific properties 
        if (blogDto.getTitle() != null){

            // Still should throw an exception if title is empty
            if (blogDto.getTitle().isEmpty()) {
                
                throw new BlogBadRequestException("Invalid Title Type");
            }

            blog.setTitle(blogDto.getTitle());
        }
        // Checks that title is not null - null will not throw an exception as you 
        // should be able to only update specific properties 
        if (blogDto.getMessage() != null) {

            // Still should throw an exception if message is empty
            if (blogDto.getMessage().isEmpty()) {
               
                throw new BlogBadRequestException("Invalid Message Type");
            
            }

            blog.setMessage(blogDto.getMessage());
        }

        Blog updatedBlog = blogRepository.save(blog);

        return mapToBlogDto(updatedBlog);
    }

    @Override
    public void deleteBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog could not be found for deletion"));
        blogRepository.delete(blog);
    }

    // method to convert a Blog to a BlogDto
    private BlogDto mapToBlogDto(Blog blog) {
        BlogDto blogDto = BlogDto.builder()
            .id(blog.getId())
            .title(blog.getTitle())
            .message(blog.getMessage())
            .build();

        return blogDto;
    }

    // Method to convert a BlogDto to a Blog
    private Blog mapToBlog(BlogDto blogDto) {
        Blog blog = Blog.builder()
            .title(blogDto.getTitle())
            .message(blogDto.getMessage())
            .build();

        return blog;
    }
}