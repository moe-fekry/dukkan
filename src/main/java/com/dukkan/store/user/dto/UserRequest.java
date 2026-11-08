package com.dukkan.store.user.dto;

import jakarta.validation.constraints.*;

public record UserRequest (
    @NotBlank(message = "name is required")
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters")
    String name,
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    String email,
    @NotBlank(message = "password is required")
    @Size(min = 8, max = 30)
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "password must contain at least one letter and one number"
    )
    String password,
    String address,
    String phoneNumber,
    @NotEmpty(message = "roles are required")
    String roles,
    @NotNull(message = "enabled is required")
    boolean activate
){}
