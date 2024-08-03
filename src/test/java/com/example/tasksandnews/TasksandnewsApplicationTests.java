package com.example.tasksandnews;

import com.example.tasksandnews.model.Task;
import com.example.tasksandnews.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TasksandnewsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
	void testCreateTask() throws Exception {
		Task task = new Task("New Task", "This is a new task", false);

		mockMvc.perform(post("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\": \"" + task.getTitle() + "\", \"description\": \"" + task.getDescription() + "\", \"completed\": " + task.isCompleted() + "}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value(task.getTitle()))
				.andExpect(jsonPath("$.description").value(task.getDescription()))
				.andExpect(jsonPath("$.completed").value(task.isCompleted()));
	}

	@Test
	void testUpdateTask() throws Exception {
		Task task = new Task("Existing Task", "Update this task", false);
		task = taskRepository.save(task);

		String updatedTaskJson = "{\"title\": \"Updated Task\", \"description\": \"This task has been updated\", \"completed\": true}";

		mockMvc.perform(put("/api/tasks/" + task.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(updatedTaskJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Updated Task"))
				.andExpect(jsonPath("$.description").value("This task has been updated"))
				.andExpect(jsonPath("$.completed").value(true));
	}


    @Test
    void testDeleteTask() throws Exception {
        Task task = new Task("Task to be deleted", "This task will be deleted", false);
        task = taskRepository.save(task);

        mockMvc.perform(delete("/api/tasks/" + task.getId()))
                .andExpect(status().isNoContent());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
}
