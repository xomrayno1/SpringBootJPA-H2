package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	@Override
	List<Employee> findAll();
	
	
	Employee findByFirstName(String search);
}
