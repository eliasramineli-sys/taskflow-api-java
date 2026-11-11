package dev.portfolio.taskflow.task;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record TaskRequest(
    @NotBlank @Size(max = 120) String title,
    @Size(max = 1000) String description,
    LocalDate dueDate,
    @NotNull TaskStatus status
) {}
