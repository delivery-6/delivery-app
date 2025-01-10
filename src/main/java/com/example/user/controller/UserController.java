package com.example.user.controller;

import com.example.user.dto.MeResponseDto;
import com.example.user.dto.RegisterRequestDto;
import com.example.user.dto.RegisterResponseDto;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import com.example.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody RegisterRequestDto request) {
        // 회원가입 처리
        User user = userService.registerUser(request.getEmail(), request.getPassword(), request.getRole());

        // 응답 반환
        RegisterResponseDto response = RegisterResponseDto.of(user.getEmail());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/me")
    public ResponseEntity<MeResponseDto> getAuthenticatedUserInfo() {
        // 인증된 사용자의 ID와 역할 목록을 가져옵니다.
        Integer userId = AuthUtil.getId();
        List<String> userRoles = AuthUtil.getRoles();

        // DTO를 사용하여 응답 반환
        MeResponseDto response = MeResponseDto.of(userId, userRoles);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content 반환
    }

}
