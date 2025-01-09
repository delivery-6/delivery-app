package com.example.user.repository;


import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
