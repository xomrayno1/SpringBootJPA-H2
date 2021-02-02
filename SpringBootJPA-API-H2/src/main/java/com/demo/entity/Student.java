package com.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "required")
	@Size(min = 2 , max = 26 ,message = "Size column 3 - 26")
	private String firstName;
	@NotBlank(message = "required")
	@Size(min = 2 , max = 26 ,message = "Size column 3 - 26")
	private String lastName;
	@ManyToMany
	@JoinTable(name = "student_course",
				joinColumns = @JoinColumn(name="student_id"),
				inverseJoinColumns = @JoinColumn(name="course_id"))
	 
	private List<Course>  courses;
	
	@NotBlank(message = "required")
	@Size(min = 4 , max = 26 ,message = "Size column 4 - 26")
	@JsonProperty("code")
	private String codeStudent;
	
	@OneToMany(mappedBy = "student")
	@JsonIgnore
	private List<Address> address;
	
	
	private Date createDate;
	private Date updateDate;
	
	
	
	public Student( ) {
 
	}
	
	public Student(
			@NotBlank(message = "required") @Size(min = 2, max = 26, message = "Size column 3 - 26") String firstName,
			@NotBlank(message = "required") @Size(min = 2, max = 26, message = "Size column 3 - 26") String lastName,
			@NotBlank(message = "required") @Size(min = 4, max = 26, message = "Size column 4 - 26") String codeStudent) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.codeStudent = codeStudent;
	}

	public Student(long id) {
		 
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCodeStudent() {
		return codeStudent;
	}
	public void setCodeStudent(String codeStudent) {
		this.codeStudent = codeStudent;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	
	
}
