package com.christian.todoapi.service;

import com.christian.todoapi.dto.CreateTagDto;
import com.christian.todoapi.dto.EditTagDto;
import com.christian.todoapi.dto.GetTagDto;
import com.christian.todoapi.error.TagNotFoundException;
import com.christian.todoapi.model.Tag;
import com.christian.todoapi.repos.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<GetTagDto> getAllTags() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public GetTagDto getTagById(Long id) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(id));

        return toDto(tag);
    }

    public GetTagDto createTag(CreateTagDto dto) {

        Tag tag = Tag.builder()
                .name(dto.name())
                .build();

        Tag saved = repository.save(tag);

        return toDto(saved);
    }

    public GetTagDto updateTag(Long id, EditTagDto dto) {

        Tag tag = repository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(id));

        tag.setName(dto.name());

        Tag saved = repository.save(tag);

        return toDto(saved);
    }

    public void deleteTag(Long id) {

        Tag tag = repository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(id));

        repository.delete(tag);
    }

    private GetTagDto toDto(Tag tag) {
        return new GetTagDto(
                tag.getId(),
                tag.getName()
        );
    }
}