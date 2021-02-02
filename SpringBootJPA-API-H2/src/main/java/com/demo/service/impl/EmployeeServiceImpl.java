package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Employee;
import com.demo.repository.EmployeeRepository;
import com.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		empRepo.delete(employee);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

	@Override
	public Employee findById(long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id).orElse(null);
	}

	@Override
	public Employee findByFirstName(String search) {
		// TODO Auto-generated method stub
		return empRepo.findByFirstName(search);
	}

}
