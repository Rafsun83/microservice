package com.example.self_management.controller;

import com.example.self_management.model.domain.Task;
import com.example.self_management.model.dto.task.CreateTaskRequest;
import com.example.self_management.model.dto.task.UpdateTaskRequest;
import com.example.self_management.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name="All Tasks", description = "Get all task")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "All Task", description = "Get all task for this system")
    @GetMapping
    public List<Task> getAllTasks(@ParameterObject Pageable pageable) {
        return taskService.getAllTasks(pageable);
    }

    @Operation(summary = "Get task by Id", description = "Get task by Id through the API")
    @GetMapping("{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Create Task", description = "Create Task API")
    @PostMapping
    public Long createTask(@RequestBody CreateTaskRequest  createTaskRequest) {
        return  taskService.createTask(createTaskRequest);
    }

    @Operation(summary = "Update task", description = "Update task by this")
    @PutMapping("{id}")
    public void updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        taskService.updateTask(id, updateTaskRequest);
    }


    @Operation(summary = "Delete Task", description = "Delete task through the API")
    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
