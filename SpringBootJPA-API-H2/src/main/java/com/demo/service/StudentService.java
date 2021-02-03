package com.demo.service;

import java.util.List;

import com.demo.entity.Course;
import com.demo.entity.Student;

public interface StudentService {
	
	 Student createStudent(Student student);
	 Student updateStudent(Student student);
	 void deleteStudent(Student student);
	 List<Student> getAll();
	 Student getById(long id);
	 List<Student> getByCourse(Course course);
	 boolean isExists(String code);
	 Student getOne(long id);
	 
}
