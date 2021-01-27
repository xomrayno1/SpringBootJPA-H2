package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Address;
import com.demo.entity.Student;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{
	List<Address> findByStudent(Student student);
}
