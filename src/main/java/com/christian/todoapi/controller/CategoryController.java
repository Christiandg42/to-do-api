package com.christian.todoapi.controller;

import com.christian.todoapi.dto.CreateCategoryDto;
import com.christian.todoapi.dto.EditCategoryDto;
import com.christian.todoapi.dto.GetCategoryDto;
import com.christian.todoapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Controller", description = "Operaciones para gestionar categorías")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorías")
    public List<GetCategoryDto> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por su id")
    public GetCategoryDto getCategoryById(
            @Parameter(description = "Identificador de la categoría")
            @PathVariable Long id
    ) {
        return service.getCategoryById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva categoría")
    public GetCategoryDto createCategory(@RequestBody CreateCategoryDto dto) {
        return service.createCategory(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría existente")
    public GetCategoryDto updateCategory(
            @Parameter(description = "Identificador de la categoría")
            @PathVariable Long id,
            @RequestBody EditCategoryDto dto
    ) {
        return service.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría existente")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "Identificador de la categoría")
            @PathVariable Long id
    ) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}