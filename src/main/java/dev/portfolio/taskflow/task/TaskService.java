package dev.portfolio.taskflow.task;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TaskService {
    private final TaskRepository repository;
    public TaskService(TaskRepository repository) { this.repository = repository; }
    @Transactional
    public TaskResponse create(TaskRequest request) {
        Task task = new Task(request.title().trim(), request.description(), request.dueDate());
        task.update(request.title().trim(), request.description(), request.dueDate(), request.status());
        return TaskResponse.from(repository.saveAndFlush(task));
    }
    @Transactional(readOnly = true)
    public TaskResponse get(Long id) { return TaskResponse.from(find(id)); }
    @Transactional(readOnly = true)
    public Page<TaskResponse> list(TaskStatus status, Pageable pageable) {
        Page<Task> page = status == null ? repository.findAll(pageable) : repository.findByStatus(status, pageable);
        return page.map(TaskResponse::from);
    }
    @Transactional
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = find(id);
        task.update(request.title().trim(), request.description(), request.dueDate(), request.status());
        return TaskResponse.from(repository.saveAndFlush(task));
    }
    @Transactional
    public void delete(Long id) { repository.delete(find(id)); }
    private Task find(Long id) { return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)); }
}
