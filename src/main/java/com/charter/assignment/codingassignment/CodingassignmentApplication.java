package com.charter.assignment.codingassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.charter.assignment")
public class CodingassignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingassignmentApplication.class, args);
	}

}
