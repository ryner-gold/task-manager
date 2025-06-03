package com.ryner.taskmanager;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
  public Task findTaskById(@PathVariable Long id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    return optionalTask.orElseGet(Task::new);
  }

  @PostMapping("/tasks")
  public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
    return new ResponseEntity<>(taskRepository.save(task), HttpStatus.CREATED);
  }

  @PutMapping("/tasks/{id}")
  public void updateTaskById(@PathVariable Long id, @RequestBody Task task) {
    Optional<Task> taskToUpdate = taskRepository.findById(id);
    if (taskToUpdate.isPresent()) {
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
