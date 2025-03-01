package com.example.taskmanager.service;

import com.example.taskmanager.entity.TaskCategory;
import com.example.taskmanager.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<TaskCategory> getAllCategories() {
        return taskCategoryRepository.findAll();
    }

    public Optional<TaskCategory> getCategoryById(Long id) {
        return taskCategoryRepository.findById(id);
    }

    public Map<String, String> saveCategory(TaskCategory category) {
        Map<String, String> response = new HashMap<>();

        try {
            if (taskCategoryRepository.findByName(category.getName()).isPresent()) {
                response.put("error", "Category with name '" + category.getName() + "' already exists.");
                return response;
            }

            TaskCategory savedCategory = taskCategoryRepository.save(category);
            response.put("message", "Category created successfully.");
            response.put("id", String.valueOf(savedCategory.getId()));
            return response;
        } catch (Exception e) {
            response.put("error", "An unexpected error occurred: " + e.getMessage());
            return response;
        }
    }


    public TaskCategory updateCategory(Long id, TaskCategory updatedCategory) {
        return taskCategoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return taskCategoryRepository.save(category);
                })
                .orElseThrow(() -> new RuntimeException("Category with ID " + id + " does not exist."));
    }

    public Map<String, String> deleteCategory(Long id) {
        Map<String, String> response = new HashMap<>();

        if (!taskCategoryRepository.existsById(id)) {
            response.put("message", "Category with ID " + id + " does not exist.");
            return response;
        }

        taskCategoryRepository.deleteById(id);
        response.put("message", "Category with ID " + id + " has been successfully deleted.");
        return response;
    }
}
