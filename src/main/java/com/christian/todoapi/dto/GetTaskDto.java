package com.christian.todoapi.dto;

import com.christian.todoapi.model.TaskPriority;
import com.christian.todoapi.model.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record GetTaskDto(
        Long id,
        LocalDateTime createdAt,
        String title,
        String description,
        LocalDate deadline,
        TaskStatus status,
        TaskPriority priority,
        String ownerUsername,
        String categoryName,
        List<String> tagNames
) {
}