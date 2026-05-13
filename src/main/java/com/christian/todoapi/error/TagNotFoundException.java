package com.christian.todoapi.error;

public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(Long id) {
        super("No se ha encontrado la etiqueta con id: " + id);
    }
}