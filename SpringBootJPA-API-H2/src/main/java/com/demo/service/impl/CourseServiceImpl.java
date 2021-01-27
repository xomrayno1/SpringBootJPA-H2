package com.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Course;
import com.demo.entity.Student;
import com.demo.repository.CourseRepository;
import com.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepo;
	@Override
	public Course createCourse(Course course) {
		// TODO Auto-generated method stub
		course.setCreateDate(new Date());
		course.setUpdateDate(new Date());
		return courseRepo.save(course);
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		course.setUpdateDate(new Date());
		return courseRepo.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub
		courseRepo.delete(course);
	}

	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}

	@Override
	public Course getById(long id) {
		// TODO Auto-generated method stub
		Optional<Course> option  = courseRepo.findById(id);
		if(!option.isPresent()) {
			return null;
		}
		return courseRepo.findById(id).orElse(null);
	}
 

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		courseRepo.deleteAll();
	}

	@Override
	public List<Course> getByStudents(Student student) {
		// TODO Auto-generated method stub
		return courseRepo.findByStudents(student);
	}

	@Override
	public boolean isExists(String name) {
		// TODO Auto-generated method stub
		return courseRepo.findByName(name) != null ? true : false;
		 
	}

}
