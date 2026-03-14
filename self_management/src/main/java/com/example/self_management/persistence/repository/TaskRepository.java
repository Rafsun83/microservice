package com.example.self_management.persistence.repository;

import com.example.self_management.model.domain.Task;
import com.example.self_management.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
