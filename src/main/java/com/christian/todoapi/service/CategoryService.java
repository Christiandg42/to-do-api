package com.christian.todoapi.service;

import com.christian.todoapi.dto.CreateCategoryDto;
import com.christian.todoapi.dto.EditCategoryDto;
import com.christian.todoapi.dto.GetCategoryDto;
import com.christian.todoapi.error.CategoryNotFoundException;
import com.christian.todoapi.model.Category;
import com.christian.todoapi.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<GetCategoryDto> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public GetCategoryDto getCategoryById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        return toDto(category);
    }

    public GetCategoryDto createCategory(CreateCategoryDto dto) {

        Category category = Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();

        Category saved = repository.save(category);

        return toDto(saved);
    }

    public GetCategoryDto updateCategory(Long id, EditCategoryDto dto) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(dto.name());
        category.setDescription(dto.description());

        Category saved = repository.save(category);

        return toDto(saved);
    }

    public void deleteCategory(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        repository.delete(category);
    }

    private GetCategoryDto toDto(Category category) {
        return new GetCategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}