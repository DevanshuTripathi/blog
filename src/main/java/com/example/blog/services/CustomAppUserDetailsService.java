package com.example.blog.services;

import com.example.blog.models.AppUser;
import com.example.blog.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAppUserDetailsService implements UserDetailsService {

    @Autowired
    private final AppUserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public CustomAppUserDetailsService(AppUserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles("USER")
                .build();
    }

    public void registerNewUser(AppUser registrationDetails) {
        if (repo.existsByUsername(registrationDetails.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        String encodePassword = passwordEncoder.encode(registrationDetails.getPassword());
        registrationDetails.setPassword(encodePassword);

        repo.save(registrationDetails);
    }
}
