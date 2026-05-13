package com.christian.todoapi.dto;

public record CreateCategoryDto(
        String name,
        String description
) {
}