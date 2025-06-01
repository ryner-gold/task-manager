package com.ryner.taskmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Task {
    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  public Task(String title){
    this.title = title;
  }
  @Override
  public String toString() {
    return "Task{" + "id=" + id + ", title='" + title + '\'' + '}';
  }
}
