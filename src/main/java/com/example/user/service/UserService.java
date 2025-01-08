package com.example.user.service;

import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String rawPassword, Role role) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use: " + email);
        }

        // 사용자 생성 및 저장
        User user = new User();
        user.setName("name");
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        return userRepository.save(user);
    }
}
