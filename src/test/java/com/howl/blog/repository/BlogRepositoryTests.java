package com.howl.blog.repository;


import java.sql.Date;
import java.sql.Time;
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
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet")
            .publishDate(date)
            .publishTime(time)
            .build();

        blogRepository.save(blog); 

        Assertions.assertThat(blog).isNotNull();
        Assertions.assertThat(blog.getId()).isGreaterThan(0);
    }

    @Test
    public void getBlogTest() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet")
            .publishDate(date)
            .publishTime(time)
            .build();

        this.blogRepository.save(blog);

        Blog foundBlog = this.blogRepository.findById(1L).get();

        Assertions.assertThat(foundBlog.getId()).isEqualTo(1L);
        Assertions.assertThat(foundBlog.getTitle()).isEqualTo("Rock Climbing Grip Strength");
        Assertions.assertThat(foundBlog.getAuthor()).isEqualTo("Ryan Wallace");
        Assertions.assertThat(foundBlog.getCategory()).isEqualTo("Sports");
        Assertions.assertThat(foundBlog.getText()).isEqualTo("Lorem ipsum dolor sit amet");
        Assertions.assertThat(foundBlog.getPublishDate()).isEqualTo(date);
        Assertions.assertThat(foundBlog.getPublishTime()).isEqualTo(time);
    }

    @Test
    public void getAllBlogsTest() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        Blog blog1 = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet")
            .publishDate(date)
            .publishTime(time)
            .build();

        Blog blog2 = Blog.builder()
            .title("Rocket League Mechanics")
            .author("")
            .category("Sports")
            .text("consectetur adipiscing elit")
            .publishDate(date)
            .publishTime(time)
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
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet")
            .publishDate(date)
            .publishTime(time)
            .build();

        this.blogRepository.save(blog);

        Blog foundBlog = this.blogRepository.findById(1L).get();

        long newMillis = System.currentTimeMillis();
        Date newDate = new Date(newMillis);
        Time newTime = new Time(newMillis);

        foundBlog.setTitle("new title");
        foundBlog.setAuthor("new author");
        foundBlog.setCategory("new category");
        foundBlog.setText("new text");
        foundBlog.setText("new text");
        foundBlog.setPublishDate(newDate);
        foundBlog.setPublishTime(newTime);

        Blog updatedBlog = this.blogRepository.save(foundBlog);

        Assertions.assertThat(updatedBlog.getId()).isEqualTo(1L);
        Assertions.assertThat(updatedBlog.getTitle()).isEqualTo("new title");
        Assertions.assertThat(updatedBlog.getAuthor()).isEqualTo("new author");
        Assertions.assertThat(updatedBlog.getCategory()).isEqualTo("new category");
        Assertions.assertThat(updatedBlog.getText()).isEqualTo("new text");
        Assertions.assertThat(updatedBlog.getPublishDate()).isEqualTo(newDate);
        Assertions.assertThat(updatedBlog.getPublishTime()).isEqualTo(newTime);
    }

    @Test
    public void deleteBlogPostById() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet")
            .publishDate(date)
            .publishTime(time)
            .build();

        this.blogRepository.save(blog);

        this.blogRepository.deleteById(blog.getId());
        Optional<Blog> blogReturnAfterDelete = this.blogRepository.findById(blog.getId());

        Assertions.assertThat(blogReturnAfterDelete).isEmpty();
        
    }
}
