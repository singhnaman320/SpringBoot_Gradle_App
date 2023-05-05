package com.gradle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.models.Product;
import com.gradle.models.ProductDTO;
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
	
	// 2. Getting all the products
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProductsHandler(){
			
		List<Product> gettingList= pservice.getAllProducts();
			
		return new ResponseEntity<List<Product>>(gettingList, HttpStatus.OK);
	}
	
	// 3. Updating product details
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProductHandler(@Valid @RequestBody Product product){
			
		Product updateProductDetails = pservice.updateProduct(product);
			
		return new ResponseEntity<Product>(updateProductDetails, HttpStatus.ACCEPTED);
	}
	
	// 4. Delete product by its Id
	
	@DeleteMapping("/products/{Id}")
	public ResponseEntity<Product> deleteProductByIdHandler(@PathVariable("Id") Integer productId){
			
		Product deleteProduct = pservice.deleteProductById(productId);
			
		return  new ResponseEntity<Product>(deleteProduct, HttpStatus.ACCEPTED);
	}
	
	// 5. Get products by its category
	
	@GetMapping("/products/{cat}")
	public ResponseEntity<List<Product>> getProductByCategoryHandler(@PathVariable("cat") String category){
			
		List<Product> productByCategory = pservice.getsProductByCategory(category); 
			
		return new ResponseEntity<List<Product>>(productByCategory, HttpStatus.CREATED);
	}
	
	// 6. get Product Name, Quantity, Price
	
	@GetMapping("/productsDto")
	public ResponseEntity<List<ProductDTO>> getProductNameQtyPriceHandler(){
			
		List<ProductDTO> productNameQuantityPrice = pservice.getProductNameQtyPrice();
			
		return new ResponseEntity<List<ProductDTO>>(productNameQuantityPrice,HttpStatus.OK);
	}
	
}
