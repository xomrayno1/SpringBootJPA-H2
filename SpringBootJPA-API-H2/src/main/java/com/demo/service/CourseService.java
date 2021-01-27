package com.demo.service;

import java.util.List;

import com.demo.entity.Course;
import com.demo.entity.Student;

public interface CourseService {
	 Course createCourse(Course	course);
	 Course updateCourse(Course	 course);
	 void deleteCourse(Course course);
	 List<Course> getAll();
	 Course getById(long id);
	 List<Course> getByStudents(Student student);
	 void deleteAll();
	 boolean isExists(String name);
}
