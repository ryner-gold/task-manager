package com.ryner.taskmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskmanagerApplication {

  private static final Logger log = LoggerFactory.getLogger(TaskmanagerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(TaskmanagerApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(TaskRepository repository) {
    return (args) -> {
      // save some tasks
      repository.save(new Task( "title"));
      repository.save(new Task("title2"));
      repository.save(new Task("title3"));

      // fetch all tasks
      log.info("Tasks found with findAll():");
      log.info("-------------------------------");
      repository
          .findAll()
          .forEach(
              task -> {
                log.info(task.toString());
              });
      log.info("");
    };
  }
}