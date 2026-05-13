package com.christian.todoapi.controller;

import com.christian.todoapi.dto.CreateTaskDto;
import com.christian.todoapi.dto.EditTaskDto;
import com.christian.todoapi.dto.GetTaskDto;
import com.christian.todoapi.model.TaskPriority;
import com.christian.todoapi.model.TaskStatus;
import com.christian.todoapi.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Controller", description = "Operaciones para gestionar tareas")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las tareas")
    public List<GetTaskDto> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por su id")
    public GetTaskDto getTaskById(
            @Parameter(description = "Identificador de la tarea")
            @PathVariable Long id
    ) {
        return service.getTaskById(id);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Obtener tareas por estado")
    public List<GetTaskDto> getTasksByStatus(
            @Parameter(description = "Estado de la tarea: PENDING, IN_PROGRESS, COMPLETED o CANCELLED")
            @PathVariable TaskStatus status
    ) {
        return service.getTasksByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    @Operation(summary = "Obtener tareas por prioridad")
    public List<GetTaskDto> getTasksByPriority(
            @Parameter(description = "Prioridad de la tarea: LOW, MEDIUM, HIGH o URGENT")
            @PathVariable TaskPriority priority
    ) {
        return service.getTasksByPriority(priority);
    }

    @GetMapping("/tag/{tagId}")
    @Operation(summary = "Obtener tareas por etiqueta")
    public List<GetTaskDto> getTasksByTag(
            @Parameter(description = "Identificador de la etiqueta")
            @PathVariable Long tagId
    ) {
        return service.getTasksByTag(tagId);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva tarea")
    public GetTaskDto createTask(@RequestBody CreateTaskDto dto) {
        return service.createTask(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea existente")
    public GetTaskDto updateTask(
            @Parameter(description = "Identificador de la tarea")
            @PathVariable Long id,
            @RequestBody EditTaskDto dto
    ) {
        return service.updateTask(id, dto);
    }

    @PutMapping("/{taskId}/tags/{tagId}")
    @Operation(summary = "Asignar una etiqueta a una tarea existente")
    public GetTaskDto addTagToTask(
            @Parameter(description = "Identificador de la tarea")
            @PathVariable Long taskId,
            @Parameter(description = "Identificador de la etiqueta")
            @PathVariable Long tagId
    ) {
        return service.addTagToTask(taskId, tagId);
    }

    @DeleteMapping("/{taskId}/tags/{tagId}")
    @Operation(summary = "Eliminar una etiqueta de una tarea existente")
    public GetTaskDto removeTagFromTask(
            @Parameter(description = "Identificador de la tarea")
            @PathVariable Long taskId,
            @Parameter(description = "Identificador de la etiqueta")
            @PathVariable Long tagId
    ) {
        return service.removeTagFromTask(taskId, tagId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea existente")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "Identificador de la tarea")
            @PathVariable Long id
    ) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deadline/{deadline}")
    @Operation(summary = "Obtener tareas por fecha límite")
    public List<GetTaskDto> getTasksByDeadline(
            @Parameter(description = "Fecha límite de la tarea en formato YYYY-MM-DD")
            @PathVariable LocalDate deadline
    ) {
        return service.getTasksByDeadline(deadline);
    }
}