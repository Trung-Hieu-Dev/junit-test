package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudentAndGradeService {
    private final StudentDao studentDao;

    @Autowired
    public StudentAndGradeService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void createStudent(String firstName, String lastname, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstName, lastname, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }
}
