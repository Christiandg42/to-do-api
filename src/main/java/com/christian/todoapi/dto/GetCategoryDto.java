package com.christian.todoapi.dto;

public record GetCategoryDto(
        Long id,
        String name,
        String description
) {
}