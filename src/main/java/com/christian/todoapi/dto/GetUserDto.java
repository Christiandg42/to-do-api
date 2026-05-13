package com.christian.todoapi.dto;

import com.christian.todoapi.model.UserRole;

public record GetUserDto(
        Long id,
        String username,
        String email,
        String fullname,
        UserRole role
) {
}