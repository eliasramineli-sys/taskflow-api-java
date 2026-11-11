package dev.portfolio.taskflow.task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired MockMvc mvc;
    @MockitoBean TaskService service;
    @Test void invalidRequestReturns400() throws Exception {
        mvc.perform(post("/api/v1/tasks").contentType("application/json")
            .content("{\"title\":\"\",\"status\":\"PENDING\"}"))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors.title").exists());
    }
    @Test void deleteReturns204() throws Exception {
        mvc.perform(delete("/api/v1/tasks/5")).andExpect(status().isNoContent());
        verify(service).delete(5L);
    }
}
