package com.christian.todoapi.dto;

import com.christian.todoapi.model.UserRole;

public record CreateUserDto(
        String username,
        String password,
        String email,
        String fullname,
        UserRole role
) {
}