package com.example.user.controller;

import com.example.user.dto.UserRegisterRequest;
import com.example.user.dto.UserRegisterResponse;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import com.example.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest request) {
        // 회원가입 처리
        User user = userService.registerUser(request.getEmail(), request.getPassword(), request.getRole());

        // 응답 반환
        UserRegisterResponse response = new UserRegisterResponse(
                user.getEmail(),
                "User registered successfully"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Integer> getAuthenticatedUserId() {
        // 인증된 사용자의 ID를 가져옵니다.
        Integer userId = AuthUtil.getId();
        if (userId == null) {
            return ResponseEntity.status(401).build(); // 인증되지 않은 경우 401 반환
        }

        return ResponseEntity.ok(userId); // 사용자 ID 반환
    }

}
