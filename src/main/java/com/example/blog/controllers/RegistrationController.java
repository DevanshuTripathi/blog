    package com.example.blog.controllers;

    import com.example.blog.models.AppUser;
    import com.example.blog.services.CustomAppUserDetailsService;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    @Controller
    public class RegistrationController {


        private final CustomAppUserDetailsService customAppUserDetailsService;

        public RegistrationController(CustomAppUserDetailsService customAppUserDetailsService) {
            this.customAppUserDetailsService = customAppUserDetailsService;
        }

        @PostMapping("/register")
        public String registerUser(@ModelAttribute("user") AppUser user, RedirectAttributes redirectAttributes) {
            try {
                customAppUserDetailsService.registerNewUser(user);
                redirectAttributes.addFlashAttribute("successMessage", "Registration success");
                return "redirect:/login";
            } catch (RuntimeException e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/register";
            }
        }
    }
