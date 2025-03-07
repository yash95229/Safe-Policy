package com.safepolicy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safepolicy.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
