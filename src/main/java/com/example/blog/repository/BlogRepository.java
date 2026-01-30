package com.example.blog.repository;

import com.example.blog.models.Blog;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Override
    Optional<Blog> findById(Integer integer);

    @Override
    java.util.List<Blog> findAll();

}
