package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService service;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void createStudentService() {
        service.createStudent("John", "Brown", "john.brown@email.com");

        CollegeStudent student = studentDao.findByEmailAddress("john.brown@email.com");

        assertEquals("john.brown@email.com", student.getEmailAddress(), "Find by email");
    }
}
