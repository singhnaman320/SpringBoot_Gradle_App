package com.gradle.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradle.exceptions.ProductException;
import com.gradle.models.Product;
import com.gradle.models.ProductDTO;
import com.gradle.repository.ProductJpaDao;
import com.gradle.services.ProductServices;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	private ProductJpaDao dao;
	
	// 1. Registering products
	
	@Override
	public Product registerProduct(Product p) throws ProductException {
		
		boolean flag= true;
		
		if(flag) {
			
			Product saveProduct= dao.save(p);
			
			return saveProduct;
			
		}else {
			
			throw new ProductException("Unable to register product");
		}
		
	}

	// 2. Getting all the products
	
	@Override
	public List<Product> getAllProducts() throws ProductException {
		
		List<Product> allProducts= dao.findAll();
		
		if(allProducts.isEmpty()) {
			
			throw new ProductException("Unable to get all student details");
			
		}else {
			
			return allProducts;
		}
		
	}

	// 3. Updating product details
	
	@Override
	public Product updateProduct(Product p) throws ProductException {
		
		Optional<Product> opt= dao.findById(p.getProductId());
		
		if(opt.isPresent()) {
			
			//this save method perform 2 operation 1. insert 2 merge
			
			Product updatedProduct = dao.save(p);
			
			return updatedProduct;
			
		}else {
			
			throw new ProductException("Unable to update given product details");
		}
		
	}

	// 4. Delete product by its Id
	
	@Override
	public Product deleteProductById(Integer pid) throws ProductException {
		
		Optional<Product> opt = dao.findById(pid);
		
		if(opt.isPresent()) {
			
			Product existingProduct = opt.get();
			
			dao.delete(existingProduct);
			
			return existingProduct;
			
		}else {
			
			throw new ProductException("Unable to delete product details with given Id "+ pid);
		}
		
	}

	// 5. Get products by its category
	
	@Override
	public List<Product> getsProductByCategory(String category) throws ProductException {
		
		List<Product> categoryList= dao.findAll().stream().filter(c->Objects.equals(c.getCategory(), category)).collect(Collectors.toList());
		
		if(categoryList.size() !=0) {
			
			return categoryList;

		}else {
			
			throw new ProductException("Unable to get product details by its category");

		}
	}

	// 6. get Product Name, Quantity, Price
	
	@Override
	public List<ProductDTO> getProductNameQtyPrice() throws ProductException {
		
		List<ProductDTO> productDTO = new ArrayList<>();
		List<Product> allProducts = dao.findAll();
		
		for (Product p : allProducts) {
			ProductDTO pdto = new ProductDTO();
			
			pdto.setProductName(p.getProductName());
			pdto.setPquantity(p.getQuantity());
			pdto.setPrice(p.getPrice());
			
			
			productDTO.add(pdto);
		}
		if(productDTO.size()==0) {
			
			throw new ProductException("Product does not exist");
			
		}else {
			
			return productDTO;
		}
	}

}
