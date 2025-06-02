package com.ryner.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

  private final TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping("/tasks")
  public List<Task> getAllTasks() {
    Iterable<Task> source = taskRepository.findAll();
    List<Task> tasks = new ArrayList<>();
    source.forEach(tasks::add);
    return tasks;
  }

  @GetMapping("/tasks/{id}")
  public Optional<Task> findTaskById(@PathVariable Long id) {
    return taskRepository.findById(id);
  }

  @PostMapping("/tasks")
  public void createTask(@RequestBody Task task) {
    taskRepository.save(task);
  }

  @PutMapping("/tasks/{id}")
  public void updateTaskById(@PathVariable Long id, @RequestBody Task task) {
    Optional<Task> taskToUpdate = taskRepository.findById(id);
    if(taskToUpdate.isPresent()){
      Task existingTask = taskToUpdate.get();
      existingTask.setTitle(task.getTitle());
      taskRepository.save(existingTask);
    }
  }

  @DeleteMapping("/tasks/{id}")
  public void deleteTaskById(@PathVariable Long id) {
    taskRepository.deleteById(id);
  }
}
