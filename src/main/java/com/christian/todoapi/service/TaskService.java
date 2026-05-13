package com.christian.todoapi.service;

import com.christian.todoapi.dto.CreateTaskDto;
import com.christian.todoapi.dto.EditTaskDto;
import com.christian.todoapi.dto.GetTaskDto;
import com.christian.todoapi.error.TagNotFoundException;
import com.christian.todoapi.error.TaskNotFoundException;
import com.christian.todoapi.model.*;
import com.christian.todoapi.repos.CategoryRepository;
import com.christian.todoapi.repos.TagRepository;
import com.christian.todoapi.repos.TaskRepository;
import com.christian.todoapi.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository repository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository,
            UserRepository userRepository
    ) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    public List<GetTaskDto> getAllTasks() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public GetTaskDto createTask(CreateTaskDto dto) {

        Category category = null;

        if (dto.categoryId() != null) {
            category = categoryRepository.findById(dto.categoryId())
                    .orElse(null);
        }

        List<Tag> tags = new ArrayList<>();

        if (dto.tagIds() != null && !dto.tagIds().isEmpty()) {
            tags = tagRepository.findAllById(dto.tagIds());
        }

        User owner = null;

        if (dto.userId() != null) {
            owner = userRepository.findById(dto.userId())
                    .orElse(null);
        }

        Task task = Task.builder()
                .createdAt(LocalDateTime.now())
                .title(dto.title())
                .description(dto.description())
                .deadline(dto.deadline())
                .status(dto.status() != null ? dto.status() : TaskStatus.PENDING)
                .priority(dto.priority() != null ? dto.priority() : TaskPriority.MEDIUM)
                .category(category)
                .tags(tags)
                .owner(owner)
                .build();

        Task saved = repository.save(task);

        return toDto(saved);
    }

    public GetTaskDto getTaskById(Long id) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return toDto(task);
    }

    public GetTaskDto updateTask(Long id, EditTaskDto dto) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDeadline(dto.deadline());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());

        Task saved = repository.save(task);

        return toDto(saved);
    }

    public List<GetTaskDto> getTasksByStatus(TaskStatus status) {
        return repository.findByStatus(status)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<GetTaskDto> getTasksByPriority(TaskPriority priority) {
        return repository.findByPriority(priority)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<GetTaskDto> getTasksByDeadline(LocalDate deadline) {
        return repository.findByDeadline(deadline)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<GetTaskDto> getTasksByTag(Long tagId) {
        return repository.findByTags_Id(tagId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public GetTaskDto addTagToTask(Long taskId, Long tagId) {

        Task task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));

        if (task.getTags() == null) {
            task.setTags(new ArrayList<>());
        }

        if (!task.getTags().contains(tag)) {
            task.getTags().add(tag);
        }

        Task saved = repository.save(task);

        return toDto(saved);
    }

    public GetTaskDto removeTagFromTask(Long taskId, Long tagId) {

        Task task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));

        if (task.getTags() != null) {
            task.getTags().remove(tag);
        }

        Task saved = repository.save(task);

        return toDto(saved);
    }

    public void deleteTask(Long id) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        repository.delete(task);
    }

    private GetTaskDto toDto(Task task) {
        return new GetTaskDto(
                task.getId(),
                task.getCreatedAt(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.getStatus(),
                task.getPriority(),
                task.getOwner() != null ? task.getOwner().getUsername() : null,
                task.getCategory() != null ? task.getCategory().getName() : null,
                getTagNames(task)
        );
    }

    private List<String> getTagNames(Task task) {

        if (task.getTags() == null) {
            return List.of();
        }

        return task.getTags()
                .stream()
                .map(Tag::getName)
                .toList();
    }
}