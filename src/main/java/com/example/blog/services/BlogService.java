package com.example.blog.services;

import com.example.blog.models.AppUser;
import com.example.blog.models.Blog;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void CreatePost(String subject, String body, AppUser user) {
        Blog blog = new Blog();
        blog.setSubject(subject);
        blog.setBody(body);
        blog.setUser(user);

        user.addPost(blog);

        blogRepository.save(blog);
    }
}
