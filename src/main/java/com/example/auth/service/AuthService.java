package com.example.auth.service;

import com.example.auth.dto.LoginRequestDto;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginRequestDto authRequest) {
        // 사용자 정보 로드
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 비밀번호 검증
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // JWT 토큰 생성
        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().getAuthority());
    }
}
