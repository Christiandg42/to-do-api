package com.christian.todoapi.error;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("No se ha encontrado la tarea con id: " + id);
    }
}