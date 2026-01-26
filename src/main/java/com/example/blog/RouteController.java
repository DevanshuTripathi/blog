package com.example.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }
}
