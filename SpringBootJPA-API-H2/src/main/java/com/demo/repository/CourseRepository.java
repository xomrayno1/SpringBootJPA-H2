package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Course;
import com.demo.entity.Student;

@Repository
public interface CourseRepository  extends CrudRepository<Course, Long>{

	@Override
	List<Course> findAll();
	List<Course> findByStudents(Student student);
	Course findByName(String name);
}
