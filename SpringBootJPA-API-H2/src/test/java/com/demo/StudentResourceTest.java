package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.controller.StudentController;
import com.demo.entity.Student;
import com.demo.service.AddressService;
import com.demo.service.CourseService;
import com.demo.service.StudentService;
import com.demo.utils.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
//ko cần phải mock các phụ thuộc bên StudentController 
//vì SpringBootTest sẽ quét hết và lưu vào context
@WebMvcTest(value = StudentController.class)  
// nếu sử dụng @WebMvcTest thì phải mock những phụ thuộc bên StudentController
@RunWith(SpringRunner.class) //@RunWith(SpringRunner.class) @Mock
public class StudentResourceTest {
	
	//https://howtodoinjava.com/spring-boot2/testing/testing-support/
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
 
	
	@MockBean // giúp kích hoạt chế độ  giả của một lớp nhất định
	private StudentService studentService;
	
	@MockBean
	CourseService courseService;
	@MockBean
	AddressService addressService;
	@MockBean
	ResponseUtils responseUtils;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	@Test
	public void testCreateStudent() throws JsonProcessingException, Exception {
		Student student = new Student(1,"Tam","Nguyen","1755248");
 		when(studentService.createStudent(Mockito.any(Student.class)))
								.thenReturn(student);
 		ObjectMapper mapper = new ObjectMapper();
 		 	
 		MvcResult result = mockMvc.perform(
 					post("/api/v1/students").accept(MediaType.APPLICATION_JSON)
 					.content(mapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON_VALUE)
 				).andReturn();
 		System.out.println(result.getResponse().getContentAsString());
 		 
 		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
 		assertEquals("http://localhost/api/v1/students/1", 
 				result.getResponse().getHeader(HttpHeaders.LOCATION));
	}
	
 
	@Test
	public void testGetStudent() throws Exception{
		when(studentService.getById(Mockito.anyLong()))//Mockito.anyLong()
									.thenReturn(
											new Student(1,"Nguyen", "Tam", "Azzz")
											);
		MvcResult mvcResult = mockMvc.perform(
				 get("/api/v1/students/1").accept(MediaType.APPLICATION_JSON_VALUE)
				).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		String expected = "{id:1,firstName:Nguyen,lastName:Tam,code:Azzz}";
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
		Mockito.verify(studentService).getById(1);
	}
}
