package com.example.demo.unit;

import com.example.demo.controllers.StudentController;
import com.example.demo.dto.StudentDTO;
import com.example.demo.models.Student;
import com.example.demo.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.CoreMatchers.is;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    private MockMvc mockMvc;
    @Mock
    private StudentServiceImpl service;


    @BeforeEach
    public void init() {
        StudentController controller = new StudentController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void selectStudentTest() throws Exception {
        Integer id = 1;
        StudentDTO student = new StudentDTO(id, "Mark", "fowler", "pom");
        Mockito.when(service.findOne(Mockito.anyInt())).thenReturn(student);

        mockMvc.perform(get("/students/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("accountName", is(student.getAccountName())));
    }
}
