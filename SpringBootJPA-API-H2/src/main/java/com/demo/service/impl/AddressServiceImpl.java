package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Address;
import com.demo.entity.Student;
import com.demo.repository.AddressRepository;
import com.demo.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepo;
	
	@Override
	public List<Address> getByStudent(Student student) {
		// TODO Auto-generated method stub
		return addressRepo.findByStudent(student);
	}

	@Override
	public Address getById(long id) {
		// TODO Auto-generated method stub
		return addressRepo.findById(id).orElse(null);
	}

	@Override
	public Address createAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepo.save(address);
	}

	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepo.save(address);
	}

	@Override
	public void deleteById(Address address) {
		// TODO Auto-generated method stub
		addressRepo.delete(address);
	}

}
