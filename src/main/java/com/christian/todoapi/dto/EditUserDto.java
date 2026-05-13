package com.christian.todoapi.dto;

public record EditUserDto(
        String username,
        String email,
        String fullname
) {
}