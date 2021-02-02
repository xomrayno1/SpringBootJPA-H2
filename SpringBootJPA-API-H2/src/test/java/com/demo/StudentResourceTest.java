package com.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.entity.Student;
import com.demo.repository.StudentRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentResourceTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private StudentRepository studentRepo;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testStudentGetAll() throws Exception{
		when(studentRepo.findAll()).thenReturn(
				Arrays.asList(
						new Student("Tam","Nguyen","1755"),
						new Student("Tam 1","Nguyen 1","1756"),
						new Student("Tam 2","Nguyen 2","1757")
						)
				);
		MvcResult mvcResult = mockMvc.perform(
				 get("/api/v1/students").accept(MediaType.APPLICATION_JSON_VALUE)
				).andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(studentRepo).findAll();
	}
}
