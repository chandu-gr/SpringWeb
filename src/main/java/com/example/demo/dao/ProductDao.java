package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ProductEntity;

public interface ProductDao extends JpaRepository<ProductEntity, Integer>{
	
	//@Query("Select p from ProductEntity p where p.price >= ?1") //JPQL
	@Query(value = "select * from Product where price >= ?1", nativeQuery = true) //Native Query
	List<ProductEntity> findProductAbovePrice(Double price);
	
	List<ProductEntity> findByPriceBetween(Double start, Double end);
	//Select * from product where price between (start,end)
}
