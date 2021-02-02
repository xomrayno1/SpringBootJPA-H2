package com.demo.service;

import java.util.List;

import com.demo.entity.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
	List<Employee> findAll();
	Employee findById(long id);

	Employee findByFirstName(String search);
}
