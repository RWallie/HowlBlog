package com.howl.blog.controller;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howl.blog.dto.BlogDto;
// import com.howl.blog.model.Blog;
import com.howl.blog.service.BlogService;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BlogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    @Autowired
    private ObjectMapper objectMapper;

    // private Blog blog;
    private BlogDto blogDto;

    @BeforeEach
    public void init() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

        // blog = Blog.builder()
        //     .title("Rock Climbing Grip Strength")
        //     .author("Ryan Wallace")
        //     .category("Sports")
        //     .text("Lorem ipsum dolor sit amet")
        //     .publishDate(date)
        //     .publishTime(time)
        //     .build();

        blogDto = BlogDto.builder()
            .title("Rock Climbing Grip Strength")
            .author("Ryan Wallace")
            .category("Sports")
            .text("Lorem ipsum dolor sit amet")
            .publishDate(date)
            .publishTime(time)
            .build();
    }

    @Test
    public void addBlogPostTest1() throws Exception {        
        given(blogService.addBlog(ArgumentMatchers.any()))
            .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/blogs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(blogDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
