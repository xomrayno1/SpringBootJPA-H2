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

import com.demo.entity.Address;
import com.demo.entity.Course;
import com.demo.entity.Student;
import com.demo.exception.ResourceNotFoundException;
import com.demo.response.APIResponse;
import com.demo.service.AddressService;
import com.demo.service.CourseService;
import com.demo.service.StudentService;
import com.demo.utils.ResponseUtils;

@Controller
@RequestMapping("/api/v1/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;
	@Autowired
	AddressService addressService;
	@Autowired
	ResponseUtils responseUtils;
	@GetMapping
	public	ResponseEntity<APIResponse> getAll() {
		List<Student> students = 	studentService.getAll();
		if(students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return responseUtils.buildResponseSuccess(students);
	}
	@GetMapping("/{id}")
	public	ResponseEntity<Student> getById(@PathVariable("id") long id) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with id :"+id);
		}
		return new ResponseEntity<Student>(student,HttpStatus.OK);
	}
	@GetMapping("/{id}/courses") // lấy ra các khóa học của student
	public	ResponseEntity<List<Course>> getCourseByStudentId(@PathVariable("id") long id) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with id :"+id);
		}
		List<Course> courses = courseService.getByStudents(student);
		return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);
	}
 
		 
	 
		 
	 
	@PostMapping
	public	ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) { 
		if(studentService.isExists(student.getCodeStudent())) {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}else {
			Student newStudent = studentService.createStudent(student);
			if(newStudent == null) {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}else{
				URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(newStudent.getId()).toUri();
				return ResponseEntity.created(location).body(newStudent);	
			}
		}
		 
	}
	@PutMapping
	public	ResponseEntity<Object> updateStudent(@Valid @RequestBody Student student) {
		Student oldStudent = studentService.getById(student.getId());
		if(oldStudent == null) {
			throw new ResourceNotFoundException("student not found exception with  : "+student.getId());
		}else{
			if(student.getCodeStudent() != null) {
				oldStudent.setCodeStudent(student.getCodeStudent());
			}
			if(student.getFirstName() != null) {
				oldStudent.setFirstName(student.getFirstName());
			}
			if(student.getLastName() != null) {
				oldStudent.setLastName(student.getLastName());
			}
			oldStudent = studentService.updateStudent(oldStudent);
			return new ResponseEntity<Object>(oldStudent,HttpStatus.OK);
		}
	}
	@DeleteMapping("/{id}")
	public	ResponseEntity<Void> deleteStudent(@PathVariable("id") long id) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with :" + id);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} 
	}
	//lấy address từ student
	@GetMapping("/{id}/address") // lấy ra các address của student
	public	ResponseEntity<List<Address>> getAddressByStudent(@PathVariable("id") long id) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with id :"+id);
		}
		List<Address> address = addressService.getByStudent(student);
		return new ResponseEntity<List<Address>>(address,HttpStatus.OK);
	}
	@GetMapping("/{id}/address/{addressId}") // lấy ra các address của student
	public	ResponseEntity<Address> getAddressByStudentandAddress(
			@PathVariable("id") long id,
			@PathVariable("addressId") long addressId) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with id :"+id);
		}else {
			Address address = addressService.getById(addressId);
			if(address == null) {
				throw new ResourceNotFoundException("address not found exception with id :"+id);
			}else {
				return new ResponseEntity<Address>(address,HttpStatus.OK);
			}
		}
	}
	@PostMapping("/{id}/address")
	public	ResponseEntity<Object> createAddress(@PathVariable("id") long id,
							@Valid @RequestBody Address address) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with :" + id);
		}else {
			Address newAddress = addressService.createAddress(address);
			if(newAddress == null) {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}else {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(newAddress.getId()).toUri();
				return ResponseEntity.created(location).body(newAddress);
			}			 
		}
	}
	@PutMapping("/{id}/address")
	public	ResponseEntity<Address> updateAddress(@PathVariable("id") long id,
							@Valid @RequestBody Address address) {
		Student student = studentService.getById(id);
		if(student == null) {
			throw new ResourceNotFoundException("student not found exception with :" + id);
		}else {
			Address oldAddress = addressService.getById(address.getId());
			if(oldAddress == null) {
				throw new ResourceNotFoundException("address not found exception with :" + address.getId());
			}else{
				if(address.getDistrict() != null) {
					oldAddress.setDistrict(address.getDistrict());
				}
				if(address.getProvince() != null) {
					oldAddress.setProvince(address.getProvince());
				}
				if(address.getWard() != null) {
					oldAddress.setWard(address.getWard());
				}
				oldAddress = addressService.updateAddress(address);
				return new ResponseEntity<Address>(oldAddress,HttpStatus.OK);
			}	 
		}
	}
	
}
