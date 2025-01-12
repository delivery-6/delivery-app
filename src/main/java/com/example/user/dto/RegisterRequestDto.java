package com.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private final String email;

    @NotBlank(message = "Password cannot be blank")
    private final String password;

    @NotNull(message = "Role is required")
    private final String role;
}
