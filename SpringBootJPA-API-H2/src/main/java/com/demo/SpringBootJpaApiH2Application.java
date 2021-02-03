package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.entity.Addres;
import com.demo.entity.Address;
import com.demo.entity.Student;
import com.demo.service.AddressService;
import com.demo.service.StudentService;
/*
 * https://www.javaguides.net/2018/06/restful-api-design-best-practices.html
 */
@SpringBootApplication
public class SpringBootJpaApiH2Application implements CommandLineRunner{
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private AddressService addressService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApiH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Student student =  studentService.getOne(1); 
		// ko call databbase chỉ tạo 1 cái proxy 
		//Student student = studentService.getById(1);
		//call database và lấy ra value 1 trường
		Address address = new Address("PY","DH","P HHT",student);
		
		addressService.createAddress(address);
		
	}

}
