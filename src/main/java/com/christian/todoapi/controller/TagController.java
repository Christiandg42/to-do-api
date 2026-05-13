package com.christian.todoapi.controller;

import com.christian.todoapi.dto.CreateTagDto;
import com.christian.todoapi.dto.EditTagDto;
import com.christian.todoapi.dto.GetTagDto;
import com.christian.todoapi.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@Tag(name = "Tag Controller", description = "Operaciones para gestionar etiquetas")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las etiquetas")
    public List<GetTagDto> getAllTags() {
        return service.getAllTags();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una etiqueta por su id")
    public GetTagDto getTagById(
            @Parameter(description = "Identificador de la etiqueta")
            @PathVariable Long id
    ) {
        return service.getTagById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva etiqueta")
    public GetTagDto createTag(@RequestBody CreateTagDto dto) {
        return service.createTag(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una etiqueta existente")
    public GetTagDto updateTag(
            @Parameter(description = "Identificador de la etiqueta")
            @PathVariable Long id,
            @RequestBody EditTagDto dto
    ) {
        return service.updateTag(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una etiqueta existente")
    public ResponseEntity<Void> deleteTag(
            @Parameter(description = "Identificador de la etiqueta")
            @PathVariable Long id
    ) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}