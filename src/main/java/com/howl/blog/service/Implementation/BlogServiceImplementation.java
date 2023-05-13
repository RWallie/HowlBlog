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
        BlogDto blogDto = new BlogDto();

        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());   
        blogDto.setAuthor(blog.getAuthor());
        blogDto.setCategory(blog.getCategory());
        blogDto.setText(blog.getText());
        blogDto.setPublishDate(blog.getPublishDate());
        blogDto.setPublishTime(blog.getPublishTime());

        return blogDto;
    }

    private Blog mapToBlog(BlogDto blogDto) {
        Blog blog = new Blog();

        blog.setTitle(blogDto.getTitle());   
        blog.setAuthor(blogDto.getAuthor());
        blog.setCategory(blogDto.getCategory());
        blog.setText(blogDto.getText());
        blog.setPublishDate(blogDto.getPublishDate());
        blog.setPublishTime(blogDto.getPublishTime());

        return blog;
    }
}