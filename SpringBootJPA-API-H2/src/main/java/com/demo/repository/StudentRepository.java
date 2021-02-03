package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Course;
import com.demo.entity.Student;

@Repository
public interface StudentRepository extends  JpaRepository<Student, Long>{

	@Override
	List<Student> findAll();
	List<Student> findByCourses(Course courses);
	Student findByCodeStudent(String code);
 
}
