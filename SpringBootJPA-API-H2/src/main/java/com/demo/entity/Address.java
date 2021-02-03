package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String province;
	private String district;
	private String ward;
	
	@ManyToOne
	@JoinColumn(name = "student_id")

	private Student student;
	
	
	
	
	
	public Address() {
		super();
	}
	public Address(String province, String district, String ward, Student student) {
		super();
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.student = student;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", province=" + province + ", district=" + district + ", ward=" + ward
				+ ", student=" + student + "]";
	}
	
	
	
	

}
