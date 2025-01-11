package com.example.user.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(staticName = "of") // 팩토리 메서드 생성
public class MeResponseDto {
    private final Integer id; // final 필드
    private final List<String> roles; // final 필드
}
