package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.dao.ProductDto;
import com.example.demo.entity.ProductEntity;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public List<ProductDto> selectAllProducts() {
		List<ProductEntity> entityList = productDao.findAll();
		List<ProductDto> dtoList = new ArrayList<>();
		for (ProductEntity entity : entityList) {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	public List<ProductDto> selectAllProductsByPrice(Double price) {
		List<ProductEntity> entityList = productDao.findProductAbovePrice(price);
		List<ProductDto> dtoList = new ArrayList<>();
		for (ProductEntity entity : entityList) {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	public List<ProductDto> selectAllProductsByPriceRange(Double start, Double end) {
		List<ProductEntity> entityList = productDao.findByPriceBetween(start, end);
		List<ProductDto> dtoList = new ArrayList<>();
		for (ProductEntity entity : entityList) {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Cacheable("prodById")
	public ProductDto selectById(Integer id) {
		ProductEntity entity = productDao.getOne(id);
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	public ProductDto insertProdut(ProductDto dto) {
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(dto, entity);
		ProductEntity savedEntity = productDao.save(entity);
		BeanUtils.copyProperties(savedEntity, dto);
		return dto;
	}
	
	public ProductDto updateProdut(ProductDto dto) {
		ProductEntity entity = productDao.getOne(dto.getProdId());
		BeanUtils.copyProperties(dto, entity);
		productDao.save(entity);
		return dto;
	}
	
	public ProductDto deleteProdut(Integer id) {
		ProductEntity entity = productDao.getOne(id);
		productDao.delete(entity);
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
}
