package com.howl.blog.repository;


import java.sql.Date;
import java.sql.Time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.howl.blog.model.Blog;

@DataJpaTest
public class BlogRepositoryTests {
    
    @Autowired
    private BlogRepository blogRepository;

    // JUnity test for saving a blog
    @Test
    public void savBlogTest() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        Blog blog = Blog.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet, consectetur adipiscing elit")
            .publishDate(date)
            .publishTime(time)
            .build();

        blogRepository.save(blog);

        Assertions.assertThat(blog.getId()).isGreaterThan(0);
    }

    @Test
    public void getBlogTest() {
        Blog blog = this.blogRepository.findById(1L).get();
    }
}
