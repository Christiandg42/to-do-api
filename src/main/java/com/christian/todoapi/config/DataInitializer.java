package com.christian.todoapi.config;

import com.christian.todoapi.model.User;
import com.christian.todoapi.model.UserRole;
import com.christian.todoapi.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .email("admin@email.com")
                    .fullname("Administrador")
                    .role(UserRole.ADMIN)
                    .build());
        }

        if (userRepository.findByUsername("gestor").isEmpty()) {
            userRepository.save(User.builder()
                    .username("gestor")
                    .password(passwordEncoder.encode("1234"))
                    .email("gestor@email.com")
                    .fullname("Gestor")
                    .role(UserRole.GESTOR)
                    .build());
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            userRepository.save(User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("1234"))
                    .email("user@email.com")
                    .fullname("Usuario")
                    .role(UserRole.USER)
                    .build());
        }
    }
}