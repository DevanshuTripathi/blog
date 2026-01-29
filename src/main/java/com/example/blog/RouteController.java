package com.example.blog;

import com.example.blog.models.AppUser;
import com.example.blog.repository.AppUserRepository;
import com.example.blog.services.CustomAppUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class RouteController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }
}
