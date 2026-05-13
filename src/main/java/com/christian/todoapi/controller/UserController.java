package com.christian.todoapi.controller;

import com.christian.todoapi.dto.CreateUserDto;
import com.christian.todoapi.dto.EditUserDto;
import com.christian.todoapi.dto.GetUserDto;
import com.christian.todoapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Operaciones para gestionar usuarios")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios")
    public List<GetUserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su id")
    public GetUserDto getUserById(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id
    ) {
        return service.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario")
    public GetUserDto createUser(@RequestBody CreateUserDto dto) {
        return service.createUser(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar perfil de usuario")
    public GetUserDto updateUser(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id,
            @RequestBody EditUserDto dto
    ) {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario existente")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id
    ) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/promote")
    @Operation(summary = "Promocionar usuario a gestor")
    public GetUserDto promoteToGestor(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id
    ) {
        return service.promoteToGestor(id);
    }

    @PutMapping("/{id}/demote")
    @Operation(summary = "Degradar gestor a usuario")
    public GetUserDto demoteToUser(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id
    ) {
        return service.demoteToUser(id);
    }
}