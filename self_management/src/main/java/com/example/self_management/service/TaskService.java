package com.example.self_management.service;

import com.example.self_management.mapper.TaskMapper;
import com.example.self_management.model.domain.Task;
import com.example.self_management.model.dto.task.CreateTaskRequest;
import com.example.self_management.model.dto.task.UpdateTaskRequest;
import com.example.self_management.persistence.entity.TaskEntity;
import com.example.self_management.persistence.repository.TaskRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> getAllTasks(Pageable pageable) {
        List<TaskEntity> getTaskList = taskRepository.findAll(pageable).getContent();
        return getTaskList.stream().map(taskMapper:: entityToTaskDomain).toList();
    }

    public Task getTaskById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElse(null);
        return taskMapper.entityToTaskDomain(taskEntity);
    }

    public Long createTask(CreateTaskRequest createTaskRequest) {
        var entityToSave = taskMapper.createTaskRequestToEntity(createTaskRequest);
        var saveTaskEntity  = taskRepository.save(entityToSave);
        return saveTaskEntity.getId();
    }

    public void updateTask(Long id, UpdateTaskRequest updateTaskRequest) {
        var entityToUpdate = taskRepository.findById(id).get();
        var saveTaskEntity = taskMapper.updateTaskRequestToEntity(updateTaskRequest, entityToUpdate);
        taskRepository.save(saveTaskEntity);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
