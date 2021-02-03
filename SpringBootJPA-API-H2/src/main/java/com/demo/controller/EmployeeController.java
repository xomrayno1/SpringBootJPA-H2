package com.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entity.Employee;
import com.demo.exception.EmployeeNotFoundException;
import com.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	 @GetMapping
	 public ResponseEntity<List<Employee>> getAll(){
		 List<Employee> employees = empService.findAll();
		 if(employees.isEmpty()) {
			 return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		 }
		 return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Employee> getById(@PathVariable("id") long id){
		 Employee employee =	empService.findById(id);
		 if(employee== null) {
			 throw new EmployeeNotFoundException("employee not found exception with id:"+id);
		 }
		 return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	 }
	 @GetMapping("/firstName/{firstName}")
	 public ResponseEntity<Employee> getById(@PathVariable("firstName") String firstName){
		 Employee employee =	empService.findByFirstName(firstName);
		 if(employee== null) {
			 throw new EmployeeNotFoundException("employee not found exception with firstName:"+firstName);
		 }
		 return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	 }
	 
	 @PostMapping
	 public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
		 Employee emp = empService.createEmployee(employee);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 		.buildAndExpand(emp.getId()).toUri();
		 return ResponseEntity.created(uri).body(emp);
	 }
	 
	 @PutMapping
	 public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		 Employee emp = empService.findById(employee.getId());
		 if(employee.getFirstName() != null) {
			 emp.setFirstName(employee.getFirstName());
		 }
		 if(employee.getLastName() != null) {
			 emp.setLastName(employee.getLastName());
		 }
		 emp = empService.updateEmployee(employee);
		 return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	 }

	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id){
		 Employee employee = empService.findById(id);
		 if(employee == null) {
			 throw new EmployeeNotFoundException("employee not found exception with id :" + id);
		 }
		 empService.deleteEmployee(employee);
		 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	 }
	 
}
