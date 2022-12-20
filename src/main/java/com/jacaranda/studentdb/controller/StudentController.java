package com.jacaranda.studentdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.studentdb.model.Student;
import com.jacaranda.studentdb.service.StudentService;


@Controller
public class StudentController {
	
	@Autowired
	StudentService repository;
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("logout")
	public String logout() {
		return "login";
	}
	
	@GetMapping({"/", "listStudents"})
	public String listStudents(Model model) {
		model.addAttribute("lista", repository.getStudents());
		return "listStudents";
	}
	
	
	@GetMapping("addStudent")
	public String addStudent(Model model) {
		Student student = new Student(); //Como se lo estoy pasando al formulario por primera vez, tengo que instanciar el objeto
		model.addAttribute("student", student);  //El primer "student" es el nombre que tendrá que recoger el formulario.
												//El segundo es el nombre del objeto que yo acabo de crear
		return "addStudent";
	}
	
	
	@PostMapping("addStudentSubmit")
	public String addStudentSubmit(@Validated @ModelAttribute("student") Student student, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addStudent";
		}else {
			repository.addStudent(student);
			return "redirect:/listStudents";
		}
	}
	
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(Model model, @RequestParam(name="id") Long id) {
		Student student = repository.getStudent(id);
		model.addAttribute("student", student);
		return "deleteStudent";
	}
	
	
	@PostMapping("/deleteStudentSubmit")
	public String deleteStudentSubmit(@ModelAttribute("student") Student student) {
		repository.deleteStudent(student);
		return "redirect:/listStudents";
	}
	
	
	@GetMapping("/editStudent")
	public String editStudent(Model model, @RequestParam(name="id") Long id) {
		Student student = repository.getStudent(id);
		model.addAttribute("student", student);
		return "editStudent";
	}
	
	@PostMapping("/editStudentSubmit")
	public String editStudentSubmit(@Validated @ModelAttribute("student") Student student,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editStudent";
		}else {
			repository.updateStudent(student);
			return "redirect:/listStudents";
		}
	}
}
