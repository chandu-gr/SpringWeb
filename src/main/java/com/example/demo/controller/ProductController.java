package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductDto;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping(value = "api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "products", method = RequestMethod.GET, produces = "application/json")
	public List<ProductDto> getAllProducts() {
		return productService.selectAllProducts();
	}
	
	@RequestMapping(value = "products/{id}", method = RequestMethod.GET, produces = "application/json")
	public ProductDto getProductsById(@PathVariable("id") Integer id) {
		return productService.selectById(id);
	}
	
	@RequestMapping(value = "products/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ProductDto getAllProducts(@PathVariable("id") Integer id) {
		return productService.deleteProdut(id);
	}
	@RequestMapping(value = "products", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public ProductDto addProduct(@RequestBody ProductDto dto) {
		return productService.insertProdut(dto);
	}
	
	@RequestMapping(value = "products", method = RequestMethod.PUT, consumes = "application/json",produces = "application/json")
	public ProductDto modifyProduct(@RequestBody ProductDto dto) {
		return productService.updateProdut(dto);
	}
	
}
