package com.jacaranda.studentdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.studentdb.model.Student;
import com.jacaranda.studentdb.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;
	
	public Student getStudent(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Student> getStudents() {
		return repository.findAll();
	}
	
	public Student addStudent(Student s) {
		return repository.save(s);
	}
	
	public void deleteStudent(Student s) {
		repository.delete(s);
	}
	
	public Student updateStudent(Student s) {
		if(getStudent(s.getId())!=null) {
			return repository.save(s);
		}else {
			return null;
		}
		
	}
	
}
