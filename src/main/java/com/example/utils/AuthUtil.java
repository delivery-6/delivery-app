package com.example.utils;

import com.example.security.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;


import java.util.List;

/**
 * 인증된 사용자 정보를 제공하는 유틸리티 클래스.
 */
@UtilityClass // 유틸리티 클래스로 선언
public class AuthUtil {

    /**
     * 현재 인증된 사용자의 ID를 반환합니다.
     *
     * @return 사용자 ID (인증되지 않은 경우 null)
     */
    public Integer getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            return userDetails.getId();
        }

        return null; // 인증되지 않은 경우
    }

    /**
     * 현재 인증된 사용자의 역할 목록을 반환합니다.
     *
     * @return 역할 목록 (인증되지 않은 경우 빈 리스트)
     */
    public List<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            // 메서드 참조 사용으로 간결화
            return userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority) // 메서드 참조로 대체
                    .toList(); // Java 16+ 사용 가능
        }

        return List.of(); // 인증되지 않은 경우 빈 리스트 반환
    }
}
