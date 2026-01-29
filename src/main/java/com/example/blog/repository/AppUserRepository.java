package com.example.blog.repository;

import com.example.blog.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findUserByUsername(String username);
    boolean existsByUsername(String username);
}
