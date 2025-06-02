package com.ryner.taskmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Task {
  public String title;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public Task(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Task{" + "id=" + id + ", title='" + title + '\'' + '}';
  }
}
