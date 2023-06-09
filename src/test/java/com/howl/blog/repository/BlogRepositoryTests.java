package com.howl.blog.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.howl.blog.model.Blog;

/*
 * Testing BlogRepository
 * @DataJpaTest annotation configures in-memory database for tests
 * @DirtiesContext annotation resets the test cache before each test
 * Testing Crud Operations:
 *  - save
 *  - findAll
 *  - findById
 *  - deleteById
 */

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BlogRepositoryTests {
    
    @Autowired
    private BlogRepository blogRepository;

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

        blogRepository.save(blog);

        Blog foundBlog = blogRepository.findById(1L).get();

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

        blogRepository.save(blog1);
        blogRepository.save(blog2); 

        List<Blog> blogList = new ArrayList<>();
        blogRepository.findAll().forEach(blogList::add);

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

        blogRepository.save(blog);

        Blog foundBlog = blogRepository.findById(1L).get();

        foundBlog.setTitle("new title");
        foundBlog.setMessage("new message");

        Blog updatedBlog = blogRepository.save(foundBlog);

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

        blogRepository.save(blog);

        blogRepository.deleteById(blog.getId());
        Optional<Blog> blogReturnAfterDelete = blogRepository.findById(blog.getId());

        Assertions.assertThat(blogReturnAfterDelete).isEmpty();
        
    }
}
