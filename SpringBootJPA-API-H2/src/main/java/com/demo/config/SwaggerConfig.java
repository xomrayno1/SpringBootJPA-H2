package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.demo.controller.CourseController;
import com.demo.controller.EmployeeController;
import com.demo.controller.StudentController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static	springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {StudentController.class,
		CourseController.class,
		EmployeeController.class})
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Java Tam")
										.apiInfo(apiInfo()).select()
										.paths(regex("/api/v1.*")).build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Student Service")
					.description("Sample Documentation Generateed Using SWAGGER2 for out Rest API")
					.termsOfServiceUrl("/webmvc")
					.license("NCT - NgChiTam")
					.licenseUrl("/TamPro").version("1.0").build();
	}
}
