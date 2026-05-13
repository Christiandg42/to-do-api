package com.christian.todoapi.repos;

import com.christian.todoapi.model.Task;
import com.christian.todoapi.model.TaskPriority;
import com.christian.todoapi.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByTags_Id(Long tagId);
}