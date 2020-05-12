package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.example.demo.controller.ProductController;
import com.example.demo.service.ProductService;

import net.minidev.json.JSONObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringWebApplicationTests {

	@Autowired
	ProductService service;
	@Autowired
	ProductController controller;
	
	@LocalServerPort
	int port;
	//unit test with Service class
	@Test
	void testSelectAllProducts() {
		assertEquals(9, service.selectAllProducts().size());
	}
	
	//Integration test with Controller class
	@Test
	void testSelectAllProductsById() {
		try {
			TestRestTemplate rt = new TestRestTemplate();
			String url = "http://localhost:"+port+"/api/products/2";
			String actualJson = rt.getForObject(url, String.class);
			//JSONObject obj = new JSONObject(actualJson);
			
			String expectedJson = "{\n" + 
					"\"prodId\": 2,\n" + 
					"\"prodName\": \"Dell Laptop\",\n" + 
					"\"price\": 80000\n" + 
					"}";
			JSONAssert.assertEquals(expectedJson, actualJson, true);
			//JSONAssert.assertEquals(expectedJson, obj, true);
	
		} catch(Exception e) {
			fail("Test failed");
		}
		
	}
	
}
