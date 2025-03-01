package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entity.TaskCategory;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskCategoryService taskCategoryService;

    // 📌 CRUD para TAREFAS

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.saveTask(taskDTO);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    @PutMapping("/{id}/status")
    public TaskDTO updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    // 📌 CRUD para CATEGORIAS

    @GetMapping("/categories")
    public List<TaskCategory> getAllCategories() {
        return taskCategoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Optional<TaskCategory> getCategoryById(@PathVariable Long id) {
        return taskCategoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<Map<String, String>> createCategory(@RequestBody TaskCategory category) {
        Map<String, String> response = taskCategoryService.saveCategory(category);

        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response); 
        }

        return ResponseEntity.ok(response); 
    }


    @PutMapping("/categories/{id}")
    public TaskCategory updateCategory(@PathVariable Long id, @RequestBody TaskCategory category) {
        return taskCategoryService.updateCategory(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(taskCategoryService.deleteCategory(id));
    }
}
