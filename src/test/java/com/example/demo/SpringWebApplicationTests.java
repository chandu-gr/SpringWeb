package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.ProductController;
import com.example.demo.service.ProductService;

@SpringBootTest
class SpringWebApplicationTests {

	@Autowired
	ProductService service;
	@Autowired
	ProductController controller;
	
	//unit test with Service class
	@Test
	void testSelectAllProducts() {
		assertEquals(9, service.selectAllProducts().size());
	}
	
	//Integration test with Controller class
	@Test
	void testGetAllProducts() {
		
	}
	
}
