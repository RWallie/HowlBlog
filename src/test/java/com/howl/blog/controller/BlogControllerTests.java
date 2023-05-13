package com.howl.blog.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howl.blog.dto.BlogDto;
import com.howl.blog.dto.BlogResponse;
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

    private BlogDto blogDto;

    @BeforeEach
    public void init() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Time time = new Time(millis);

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
    public void addBlogPostTest() throws Exception {        
        given(blogService.addBlog(ArgumentMatchers.any()))
            .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/blogs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(blogDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(blogDto.getTitle())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.author", CoreMatchers.is(blogDto.getAuthor())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(blogDto.getCategory())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.text", CoreMatchers.is(blogDto.getText())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllBlogsTest() throws Exception {    
        BlogResponse responseDto = BlogResponse.builder()
            .blogs(Arrays.asList(blogDto))
            .build();

        when(blogService.getAllBlogs()).thenReturn(responseDto.getBlogs());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/blogs"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(responseDto.getBlogs().size())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getPokemoneByIDTest() throws Exception {
        long blogId = 1;
        when(blogService.getBlogById(blogId)).thenReturn(blogDto);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/blogs/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(blogDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(blogDto.getTitle())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.author", CoreMatchers.is(blogDto.getAuthor())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(blogDto.getCategory())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.text", CoreMatchers.is(blogDto.getText())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateBlogPostByIdTest() throws Exception {     

        long blogId = 1;

        BlogDto updateBlogDto = BlogDto.builder()
            .title("updated title")
            .author("updated author")
            .category("updated category")
            .text("updated text")
            .build();        

        when(blogService.updateBlogById(blogId, updateBlogDto)).thenReturn(updateBlogDto);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/blogs/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateBlogDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(updateBlogDto.getTitle())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.author", CoreMatchers.is(updateBlogDto.getAuthor())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(updateBlogDto.getCategory())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.text", CoreMatchers.is(updateBlogDto.getText())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteBlogByIdTest() throws Exception {
        long blogId = 1;

        doNothing().when(blogService).deleteBlogById(blogId);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/blogs/1")
            .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
}
