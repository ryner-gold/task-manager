package com.ryner.taskmanager;

public class EmptyTaskObjectSentException extends Exception {
  public EmptyTaskObjectSentException(String errorMessage) {
    super(errorMessage);
  }
}
