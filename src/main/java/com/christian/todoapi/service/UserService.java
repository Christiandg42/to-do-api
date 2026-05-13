package com.christian.todoapi.service;

import com.christian.todoapi.dto.CreateUserDto;
import com.christian.todoapi.dto.EditUserDto;
import com.christian.todoapi.dto.GetUserDto;
import com.christian.todoapi.error.UserNotFoundException;
import com.christian.todoapi.model.User;
import com.christian.todoapi.model.UserRole;
import com.christian.todoapi.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<GetUserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public GetUserDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return toDto(user);
    }

    public GetUserDto createUser(CreateUserDto dto) {

        UserRole role = dto.role() != null ? dto.role() : UserRole.USER;

        User user = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .fullname(dto.fullname())
                .role(role)
                .build();

        User saved = repository.save(user);

        return toDto(saved);
    }

    public GetUserDto updateUser(Long id, EditUserDto dto) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFullname(dto.fullname());

        User saved = repository.save(user);

        return toDto(saved);
    }

    public void deleteUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        repository.delete(user);
    }

    public GetUserDto promoteToGestor(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setRole(UserRole.GESTOR);

        User saved = repository.save(user);

        return toDto(saved);
    }

    public GetUserDto demoteToUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setRole(UserRole.USER);

        User saved = repository.save(user);

        return toDto(saved);
    }

    private GetUserDto toDto(User user) {
        return new GetUserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullname(),
                user.getRole()
        );
    }
}