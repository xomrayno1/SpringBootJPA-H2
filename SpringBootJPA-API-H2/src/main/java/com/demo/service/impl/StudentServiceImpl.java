package com.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Course;
import com.demo.entity.Student;
import com.demo.repository.StudentRepository;
import com.demo.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepo;

	@Override
	public Student createStudent(Student student) {
		// TODO Auto-generated method stub
		student.setCreateDate(new Date());
		student.setUpdateDate(new Date());
		return studentRepo.save(student);
	}

	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		student.setUpdateDate(new Date());
		return studentRepo.save(student);
	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.delete(student);
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

	@Override
	public Student getById(long id) {
		// TODO Auto-generated method stub
		return studentRepo.findById(id).orElse(null);
	}

	@Override
	public List<Student> getByCourse(Course course) {
		// TODO Auto-generated method stub
		return studentRepo.findByCourses(course);
	}

	@Override
	public boolean isExists(String code) {
		// TODO Auto-generated method stub
		return studentRepo.findByCodeStudent(code) != null ? true : false;
	}
}
