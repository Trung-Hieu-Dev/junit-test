package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService service;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    JdbcTemplate jdbc;

    @BeforeEach
    public void setupDB() {
        jdbc.execute("insert into student(id, firstname, lastname, email_address) " +
                "values (1, 'Kim', 'Brown', 'kim.brown@email.com')");
    }

    @AfterEach
    public void setupCleanDB() {
        jdbc.execute("delete from student");
    }

    @Test
    public void createStudentService() {
        service.createStudent("John", "Brown", "john.brown@email.com");

        CollegeStudent student = studentDao.findByEmailAddress("john.brown@email.com");

        assertEquals("john.brown@email.com", student.getEmailAddress(), "Find by email");
    }

    @Test
    public void isStudentNullCheck() {
        assertTrue(service.checkIfStudentIsNull(1));
        assertFalse(service.checkIfStudentIsNull(0));
    }
}
