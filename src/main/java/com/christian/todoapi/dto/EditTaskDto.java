package com.christian.todoapi.dto;

import com.christian.todoapi.model.TaskPriority;
import com.christian.todoapi.model.TaskStatus;

import java.time.LocalDate;

public record EditTaskDto(
        String title,
        String description,
        LocalDate deadline,
        TaskStatus status,
        TaskPriority priority
) {
}