package com.christian.todoapi.dto;

import com.christian.todoapi.model.TaskPriority;
import com.christian.todoapi.model.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public record CreateTaskDto(
        String title,
        String description,
        LocalDate deadline,
        TaskStatus status,
        TaskPriority priority,
        Long categoryId,
        List<Long> tagIds,
        Long userId
) {
}