package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.TaskMapper;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskCategory;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.repository.TaskCategoryRepository;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id).map(TaskMapper::toDTO);
    }

    public TaskDTO saveTask(TaskDTO taskDTO) {
        TaskCategory category = taskCategoryRepository.findById(taskDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Task task = TaskMapper.toEntity(taskDTO, category);
        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskCategory category = taskCategoryRepository.findById(taskDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setCategory(category);

        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public TaskDTO updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        try {
            task.setStatus(TaskStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }

        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public Map<String, String> deleteTask(Long id) {
        Map<String, String> response = new HashMap<>();

        if (!taskRepository.existsById(id)) {
            response.put("message", "Task with ID " + id + " does not exist.");
            return response;
        }

        taskRepository.deleteById(id);
        response.put("message", "Task with ID " + id + " has been successfully deleted.");
        return response;
    }

}
