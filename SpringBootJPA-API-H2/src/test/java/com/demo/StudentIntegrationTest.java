package com.demo;

import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.entity.Student;

@SpringBootTest(classes = SpringBootJpaApiH2Application.class,
							webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class StudentIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testRetrieveStudentCourse() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response =
								restTemplate.exchange(createURLWithPort("/api/v1/students/1"),
														HttpMethod.GET,
														entity, String.class);
		String expected = "{id:1,firstName:Tam,lastName:Nguyen}";
		//System.out.println(expected);
		//System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void addStudent() {
		Student student = new Student("Tam", "Nguyen","1755555");
		HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
		ResponseEntity<String> response = 
								restTemplate.exchange(createURLWithPort("/api/v1/students"),
														HttpMethod.POST,
														entity,
														String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		System.out.println(actual);
		assertTrue(actual.contains("/api/v1/students/3"));
	}
	
	
}
