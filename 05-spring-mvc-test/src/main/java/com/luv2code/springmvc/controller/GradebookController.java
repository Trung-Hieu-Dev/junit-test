package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

	@Autowired
	private StudentAndGradeService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<CollegeStudent> students = service.getGradebook();
		m.addAttribute("students", students);
		return "index";
	}

	@PostMapping(value = "/")
	public String createStudent(@ModelAttribute("student") CollegeStudent student, Model model) {
		// save data to DB
		service.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());

		// return data to view
		Iterable<CollegeStudent> students = service.getGradebook();
		model.addAttribute("students", students);

		return "index";
	}

	@GetMapping("/studentInformation/{id}")
		public String studentInformation(@PathVariable int id, Model m) {
		return "studentInformation";
		}

}
