package com.example.tasksandnews.repository;

import com.example.tasksandnews.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
