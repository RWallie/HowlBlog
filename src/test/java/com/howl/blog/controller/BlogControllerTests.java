package com.howl.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(BlogController.class)
public class BlogControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postRequestTest() {
        RequestBuilder request = MockMvcRequestBuilders.post("/blogs");
    }
 
}
