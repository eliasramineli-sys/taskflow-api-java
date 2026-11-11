package dev.portfolio.taskflow.task;
import java.time.*;
public record TaskResponse(Long id, String title, String description, TaskStatus status,
                           LocalDate dueDate, Instant createdAt, Instant updatedAt) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(),
                task.getDueDate(), task.getCreatedAt(), task.getUpdatedAt());
    }
}
