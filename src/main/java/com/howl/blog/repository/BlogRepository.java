package com.howl.blog.repository;

import com.howl.blog.model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * BlogRepository extends JpaRepository providing methods to 
 * perform CRUD operations on the 'Blog' entity, making use 
 * of the Hibernate ORM framework.
 * 
 */

public interface BlogRepository extends JpaRepository<Blog, Long>{
  
}
