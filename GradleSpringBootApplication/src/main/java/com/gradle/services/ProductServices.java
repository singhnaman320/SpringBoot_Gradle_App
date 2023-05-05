package com.gradle.services;

import java.util.List;

import com.gradle.exceptions.ProductException;
import com.gradle.models.Product;
import com.gradle.models.ProductDTO;

public interface ProductServices {

	public Product registerProduct(Product p)throws ProductException;
	public List<Product> getAllProducts()throws ProductException;
	public Product updateProduct(Product p)throws ProductException;
	public Product deleteProductById(Integer pid)throws ProductException;
	public List<Product> getsProductByCategory(String category)throws ProductException;
	public List<ProductDTO> getProductNameQtyPrice() throws ProductException;
}
