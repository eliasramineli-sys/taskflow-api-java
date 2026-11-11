package dev.portfolio.taskflow.task;
import jakarta.persistence.*;
import java.time.*;
@Entity
@Table(name = "tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.PENDING;
    private LocalDate dueDate;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;
    protected Task() {}
    public Task(String title, String description, LocalDate dueDate) {
        this.title = title; this.description = description; this.dueDate = dueDate;
    }
    @PrePersist void beforeInsert() { createdAt = Instant.now(); updatedAt = createdAt; }
    @PreUpdate void beforeUpdate() { updatedAt = Instant.now(); }
    public void update(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = title; this.description = description; this.dueDate = dueDate; this.status = status;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
