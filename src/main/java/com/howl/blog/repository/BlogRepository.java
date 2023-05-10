package com.howl.blog.repository;

import com.howl.blog.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends CrudRepository<Blog, Long>{
  
}
