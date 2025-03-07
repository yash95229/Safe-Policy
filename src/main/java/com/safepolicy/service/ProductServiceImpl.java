package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.ProductRepository;
import com.safepolicy.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository ProductRepository;
	
	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return ProductRepository.save(product);
	}

}
