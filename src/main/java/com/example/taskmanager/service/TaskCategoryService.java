package com.example.taskmanager.service;

import com.example.taskmanager.entity.TaskCategory;
import com.example.taskmanager.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository categoryRepository;

    public List<TaskCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<TaskCategory> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public TaskCategory saveCategory(TaskCategory category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
    	categoryRepository.deleteById(id);
    }
}
