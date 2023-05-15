package com.howl.blog.repository;

import com.howl.blog.model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * BlogRepository extends JpaRepository providing methods to 
 * perform CRUD operations on the 'Blog' entity, making use 
 * of the Hibernate ORM framework.
 * JpaRepository extends:
 * CrudRepository - providing basic CRUD operations (these were used in this case)
 * PagingAndSortingRepository - provides support for pagination and sorting (can be used when scaling application)
 * 
 */

public interface BlogRepository extends JpaRepository<Blog, Long>{
  
}
