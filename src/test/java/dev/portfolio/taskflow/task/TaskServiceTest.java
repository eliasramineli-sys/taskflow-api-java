package dev.portfolio.taskflow.task;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TaskServiceTest {
    private final TaskRepository repository = mock(TaskRepository.class);
    private final TaskService service = new TaskService(repository);
    @Test void returnsNotFoundForUnknownId() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.get(999L));
    }
    @Test void createsTaskWithRequestedStatus() {
        TaskRequest request = new TaskRequest("Estudar Java", "JUnit", LocalDate.of(2027, 1, 1), TaskStatus.IN_PROGRESS);
        when(repository.saveAndFlush(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));
        TaskResponse response = service.create(request);
        assertEquals("Estudar Java", response.title());
        assertEquals(TaskStatus.IN_PROGRESS, response.status());
        verify(repository).saveAndFlush(any(Task.class));
    }
    @Test void listsEmptyPage() {
        Pageable pageable = PageRequest.of(0, 10);
        when(repository.findAll(pageable)).thenReturn(Page.empty(pageable));
        assertTrue(service.list(null, pageable).isEmpty());
    }
}
