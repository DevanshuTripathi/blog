package com.example.blog;

import com.example.blog.models.AppUser;
import com.example.blog.models.Blog;
import com.example.blog.repository.AppUserRepository;
import com.example.blog.repository.BlogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final BlogRepository blogRepository;
    private final AppUserRepository appUserRepository;

    public HomeController(BlogRepository blogRepository, AppUserRepository appUserRepository) {
        this.blogRepository = blogRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Blog> posts = blogRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("blog", new Blog());
        return "write";
    }

    @GetMapping("/blog/{blogId}")
    public String showPost(Model model, @PathVariable Integer blogId) {
        Blog post = blogRepository.findById(blogId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Post not found"
                        ));
        model.addAttribute("post", post);
        return "blog";
    }

    @GetMapping("/user/{userId}")
    public String showProfile(Model model, @PathVariable String userId) {
        AppUser user = appUserRepository.findUserByUsername(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));
        model.addAttribute("user", user);
        return "profile";
    }

}

