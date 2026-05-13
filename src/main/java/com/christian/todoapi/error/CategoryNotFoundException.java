package com.christian.todoapi.error;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long id) {
        super("No se ha encontrado la categoría con id: " + id);
    }
}