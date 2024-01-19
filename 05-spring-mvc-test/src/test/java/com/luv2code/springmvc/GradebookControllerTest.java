package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentDao studentDao;

    @Mock
    private StudentAndGradeService service;

    @BeforeEach
    public void beforeEach() {
        jdbc.execute("insert into student(id, firstname, lastname, email_address) " +
                "values (1, 'Kim', 'Brown', 'kim.brown@email.com')");
    }

    @AfterEach
    public void afterEach() {
        jdbc.execute("delete from student");
    }

    @Test
    public void getStudentsHttpRequest() throws Exception {
        // Create some test data
        CollegeStudent student1 = new CollegeStudent("John", "Brown", "john_brown@email.com");
        CollegeStudent student2 = new CollegeStudent("Mary", "Chad", "mary_chad@email.com");
        List<CollegeStudent> studentList = Arrays.asList(student1, student2);

        // set service return expected data
        when(service.getGradebook()).thenReturn(studentList);

        // check returned data by service method is correct or not
        assertIterableEquals(studentList, service.getGradebook());

        // Set up the mock service to return the test data
        when(service.getGradebook()).thenReturn(studentList);

        // Send a GET request to the home page
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andReturn();

        // get view and data
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // check returned view is index or not
        ModelAndViewAssert.assertViewName(modelAndView, "index");

    }
}
