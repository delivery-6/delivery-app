package com.example.user.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(nullable = false, length = 10)
    private String name;

    @Setter
    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "이메일 형식이 맞지 않습니다.") // 이메일 형식 검증
    @NotNull(message = "이메일을 입력하여 주세요.") // null 방지
    private String email;

    @Setter
    @Column(nullable = false, length = 30)
    private String password;

    @Setter
    private Boolean isOwner;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Setter
    @Column(nullable = false)
    private Boolean isDeleted = false;

}
