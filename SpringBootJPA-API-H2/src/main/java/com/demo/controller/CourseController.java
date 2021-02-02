package com.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entity.Course;
import com.demo.exception.ApiRequestException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.response.APIResponse;
import com.demo.service.CourseService;
import com.demo.service.StudentService;
import com.demo.utils.ResponseUtils;

@Controller
@RequestMapping("/api/v1/courses")
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;
	@Autowired
	ResponseUtils responseUtils;
	
	@GetMapping
	public ResponseEntity<APIResponse> getAll(){
		List<Course> courses = courseService.getAll();
		if(courses.isEmpty()) {
			return new ResponseEntity<APIResponse>(HttpStatus.NO_CONTENT);
		}
		return responseUtils.buildResponseSuccess(courses);
	}
//	@GetMapping("/student/{studentId}")// lấy ra các khóa học của student
//	public ResponseEntity<List<Course>> getByStudent(@PathVariable("studentId") long studentId){
//		Student student =	studentService.getById(studentId);
//		if(student == null) {
//			throw new ApiRequestException("student not found exception with id :" + studentId
//					, HttpStatus.NOT_FOUND);
//		}else{
//			List<Course> courses = courseService.getByStudents(student);
//			if(courses.isEmpty()) {
//				return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);	
//		}
//	}
	@GetMapping("/{id}")
	public ResponseEntity<Course> getById(@PathVariable("id") long id){
		Course course = courseService.getById(id);
		if(course == null) {
			throw new ResourceNotFoundException("course not found exception with id  : "+id);
		}
		return new ResponseEntity<Course>(course,HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Object> createCourse(@Valid @RequestBody Course course){
		if(courseService.isExists(course.getName())) {
			throw new ApiRequestException("Name is exists",HttpStatus.CONFLICT);
		}
		else {
			Course newCourse = courseService.createCourse(course);
			if(newCourse == null) {
				return ResponseEntity.notFound().build();
			}else {
				 				/// lấy đường dẫn và thêm pathVariable id
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(newCourse.getId()).toUri();
				//return new ResponseEntity<Object>(location,HttpStatus.CREATED);
				return ResponseEntity.created(location).body(newCourse);
			}			 
		}
		 
	}
	@PutMapping
	public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course){
		Course oldCourse = courseService.getById(course.getId());
		if(oldCourse == null) {
			throw new ApiRequestException("paramer incorrect !!");
		}else {
			if(course.getName() != null) {
				oldCourse.setName(course.getName());
			}
			oldCourse = courseService.updateCourse(oldCourse);
			return new ResponseEntity<Course>(oldCourse,HttpStatus.OK);
		}	 
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteById(@PathVariable("id") long  id){
		Course course = courseService.getById(id);
		if(course == null) {
			throw new ApiRequestException("course not found exception with  id " + id,HttpStatus.NOT_FOUND);
		}else {
			courseService.deleteCourse(course);
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}	 
	}
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		courseService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
