package com.app.todoApp.services;

import com.app.todoApp.models.Task;
import com.app.todoApp.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void createTask(String title) {
        Task task = new Task ();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepo.save(task);
    }

    public void deleteTask(Long id) {
         taskRepo.deleteById(id);
    }

    public void completeTask(Long id) {
        Task task = taskRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("invalid task ID"));
                task.setCompleted(!task.isCompleted());
                taskRepo.save(task);
    }
}
