package com.christian.todoapi.controller;

import com.christian.todoapi.dto.CreateUserDto;
import com.christian.todoapi.dto.GetUserDto;
import com.christian.todoapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Operaciones públicas de autenticación y registro")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario")
    public GetUserDto register(@RequestBody CreateUserDto dto) {
        return service.createUser(dto);
    }
}