package com.example.blog.controllers;

import com.example.blog.models.AppUser;
import com.example.blog.repository.AppUserRepository;
import com.example.blog.services.BlogService;
import com.example.blog.services.CustomAppUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PostController {

    private final BlogService blogService;
    private final AppUserRepository appUserRepository;

    public PostController(BlogService blogService, AppUserRepository appUserRepository) {
        this.blogService = blogService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/posts/create")
    public String Create(
            @RequestParam String subject,
            @RequestParam String body,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        AppUser user = appUserRepository.findUserByUsername(userDetails.getUsername())
                        .orElseThrow();
        blogService.CreatePost(subject, body, user);
        return "redirect:/home";
    }
}
