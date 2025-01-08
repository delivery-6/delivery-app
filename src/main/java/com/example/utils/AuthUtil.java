package com.example.utils;

import com.example.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    private AuthUtil() {
        // 유틸리티 클래스의 인스턴스화를 방지
    }

    /**
     * 현재 인증된 사용자의 ID를 가져옵니다.
     *
     * @return 사용자 ID (없으면 null)
     */
    public static Integer getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            return userDetails.getId(); // 사용자 ID 반환
        }

        return null; // 인증되지 않은 경우
    }
}
