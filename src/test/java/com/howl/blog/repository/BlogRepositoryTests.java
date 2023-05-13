package com.howl.blog.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.howl.blog.model.Blog;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BlogRepositoryTests {
    
    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void beforeEach() {
        this.blogRepository.deleteAll();
    }

    // JUnit test for saving a blog
    @Test
    public void saveBlogTest() {

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .message("Lorem ipsum dolor sit amet")
            .build();

        blogRepository.save(blog); 

        Assertions.assertThat(blog).isNotNull();
        Assertions.assertThat(blog.getId()).isGreaterThan(0);
    }

    @Test
    public void getBlogTest() {

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .message("Lorem ipsum dolor sit amet")
            .build();

        this.blogRepository.save(blog);

        Blog foundBlog = this.blogRepository.findById(1L).get();

        Assertions.assertThat(foundBlog.getId()).isEqualTo(1L);
        Assertions.assertThat(foundBlog.getTitle()).isEqualTo("Rock Climbing Grip Strength");
        Assertions.assertThat(foundBlog.getMessage()).isEqualTo("Lorem ipsum dolor sit amet");
    }

    @Test
    public void getAllBlogsTest() {

        Blog blog1 = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .message("Lorem ipsum dolor sit amet")
            .build();

        Blog blog2 = Blog.builder()
            .title("Rocket League Mechanics")
            .message("consectetur adipiscing elit")
            .build();

        this.blogRepository.save(blog1);
        this.blogRepository.save(blog2); 

        List<Blog> blogList = new ArrayList<>();
        this.blogRepository.findAll().forEach(blogList::add);

        Assertions.assertThat(blogList).isNotNull();
        Assertions.assertThat(blogList.size()).isEqualTo(2);
        Assertions.assertThat(blogList.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(blogList.get(1).getId()).isEqualTo(2);
    }

    @Test
    public void updateBlogPostByIdTest() {

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .message("Lorem ipsum dolor sit amet")
            .build();

        this.blogRepository.save(blog);

        Blog foundBlog = this.blogRepository.findById(1L).get();

        foundBlog.setTitle("new title");
        foundBlog.setMessage("new message");

        Blog updatedBlog = this.blogRepository.save(foundBlog);

        Assertions.assertThat(updatedBlog.getId()).isEqualTo(1L);
        Assertions.assertThat(updatedBlog.getTitle()).isEqualTo("new title");
        Assertions.assertThat(updatedBlog.getMessage()).isEqualTo("new message");
    }

    @Test
    public void deleteBlogPostById() {

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .message("Lorem ipsum dolor sit amet")
            .build();

        this.blogRepository.save(blog);

        this.blogRepository.deleteById(blog.getId());
        Optional<Blog> blogReturnAfterDelete = this.blogRepository.findById(blog.getId());

        Assertions.assertThat(blogReturnAfterDelete).isEmpty();
        
    }
}
