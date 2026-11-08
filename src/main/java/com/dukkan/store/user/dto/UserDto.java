package com.dukkan.store.user.dto;

import jakarta.validation.constraints.*;

public record UserDto (
    Integer id,
    @NotEmpty(message = "name is required")
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters")
    String name,
    @NotEmpty(message = "email are required")
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    String email,
    String address,
    String phoneNumber,
    @NotEmpty(message = "roles are required")
    String roles,
    @NotNull(message = "enabled is required")
    boolean activate
){

}
