package com.gradle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gradle.models.Product;

@Repository
public interface ProductJpaDao extends JpaRepository<Product, Integer>{

}
