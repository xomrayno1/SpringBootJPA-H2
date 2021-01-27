package com.demo.service;

import java.util.List;

import com.demo.entity.Address;
import com.demo.entity.Student;

public interface AddressService {
	
	List<Address> getByStudent(Student student);
	Address getById(long id);
	Address createAddress(Address address);
	Address updateAddress(Address address);
	void deleteById(Address address);
}
