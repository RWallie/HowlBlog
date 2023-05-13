package com.howl.blog.service.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.howl.blog.dto.BlogDto;
import com.howl.blog.exceptions.BlogNotFoundException;
import com.howl.blog.model.Blog;
import com.howl.blog.repository.BlogRepository;
import com.howl.blog.service.BlogService;

@Service
public class BlogServiceImplementation implements BlogService {

    
    private BlogRepository blogRepository;

    public BlogServiceImplementation(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) {

        Blog blog = mapToBlog(blogDto);

        Blog newBlog = blogRepository.save(blog);
        
        BlogDto blogResponse = mapToBlogDto(newBlog);

        return blogResponse;
    }   

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        //  map because it returns a list 
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

        if (blogDto.getTitle() != null){
            blog.setTitle(blogDto.getTitle());
        }
        if (blogDto.getAuthor() != null) {
            blog.setAuthor(blogDto.getAuthor());
        }
        if (blogDto.getCategory() != null) {
            blog.setAuthor(blogDto.getAuthor());
        }
        if (blogDto.getText() != null) {
            blog.setText(blogDto.getText());
        }
        if (blogDto.getPublishDate() != null) {
            blog.setPublishDate(blogDto.getPublishDate());
        }
        if (blogDto.getPublishTime() != null) {
            blog.setPublishTime(blogDto.getPublishTime());
        }

        Blog updatedBlog = blogRepository.save(blog);

        return mapToBlogDto(updatedBlog);
    }

    @Override
    public void deleteBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog could not be found for deletion"));
        blogRepository.delete(blog);
    }


    private BlogDto mapToBlogDto(Blog blog) {
        BlogDto blogDto = BlogDto.builder()
            .id(blog.getId())
            .title(blog.getTitle())
            .author(blog.getAuthor())
            .category(blog.getCategory())
            .text(blog.getText())
            .publishDate(blog.getPublishDate())
            .publishTime(blog.getPublishTime())
            .build();

        return blogDto;
    }

    private Blog mapToBlog(BlogDto blogDto) {
        Blog blog = Blog.builder()
            .title(blogDto.getTitle())
            .author(blogDto.getAuthor())
            .category(blogDto.getCategory())
            .text(blogDto.getText())
            .publishDate(blogDto.getPublishDate())
            .publishTime(blogDto.getPublishTime())
            .build();

        return blog;
    }
}