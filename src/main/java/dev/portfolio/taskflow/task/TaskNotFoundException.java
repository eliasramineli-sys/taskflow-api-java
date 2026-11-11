package dev.portfolio.taskflow.task;
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) { super("Tarefa não encontrada: " + id); }
}
