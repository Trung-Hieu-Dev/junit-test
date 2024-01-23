package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
//        student.setId(1); // only use for testing create student
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        boolean isStudent = checkIfStudentIsNull(id);

        if (isStudent) {
            studentDao.deleteById(id);
        }
    }

    public Iterable<CollegeStudent> getGradebook() {
        return studentDao.findAll();
    }
}
