package com.gradle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.models.Product;
import com.gradle.services.ProductServices;

import jakarta.validation.Valid;

@RestController
public class ProductControllers {

	@Autowired
	private ProductServices pservice;
	
	// 1. Registering Products
	
	@PostMapping("/products")
	public ResponseEntity<Product> registerProductHandler(@Valid @RequestBody Product product){
		
		Product registerProduct = pservice.registerProduct(product);
		
		return new ResponseEntity<Product>(registerProduct, HttpStatus.CREATED);
		
	}
	
	
}
