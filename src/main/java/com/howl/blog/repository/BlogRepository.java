package com.howl.blog.repository;

import com.howl.blog.model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long>{
  
}
