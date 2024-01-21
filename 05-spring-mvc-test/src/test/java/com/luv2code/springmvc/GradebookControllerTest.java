package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {
    public static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentDao studentDao;

    @Mock
    private StudentAndGradeService service;

    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();
        request.setParameter("firstname", "Chad");
        request.setParameter("lastname", "Darby");
        request.setParameter("emailAddress", "chad_darby@rmail.com");
    }

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

        // Set up the mock service to return the test data
        when(service.getGradebook()).thenReturn(studentList);

        // check returned data by service method is correct or not
        assertIterableEquals(studentList, service.getGradebook());

        // Send a GET request to the Gradebook controller
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andReturn();

        // get view and data
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // check returned view is index or not
        ModelAndViewAssert.assertViewName(modelAndView, "index");

    }

    @Test
    public void createStudentHttpRequest() throws Exception {
        // test for making sure service can return data or not
        CollegeStudent studentOne = new CollegeStudent("Eric", "Roby", "eric_roby@email.com");

        List<CollegeStudent> studentList = new ArrayList<>(List.of(studentOne));

        // Verify that getGradebook() is called once and set the return value
        when(service.getGradebook()).thenReturn(studentList); // simulate that service.getGradebook() should return studentList
        assertEquals(studentList, service.getGradebook()); // testing service.getGradebook() return studentList or not
        verify(service, times(1)).getGradebook(); // testing .getGradebook() run as expected or not. If true, it should be run 1 times only!


        // Send a POST request to the Gradebook controller
        MvcResult mvcResult = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstname", request.getParameterValues("firstname"))
                .param("lastname", request.getParameterValues("lastname"))
                .param("emailAddress", request.getParameterValues("emailAddress")))
                .andExpect(status().isOk())
                .andReturn();

        // get view and data
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // check returned view is index or not
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        // Check student was saved into DB or not
        CollegeStudent verifyStudent = studentDao.findByEmailAddress("chad_darby@rmail.com");

        assertNotNull(verifyStudent, "Student should be found");
        assertEquals("chad_darby@rmail.com", verifyStudent.getEmailAddress());
        assertEquals("Chad", verifyStudent.getFirstname());
        assertEquals("Darby", verifyStudent.getLastname());
    }
}
