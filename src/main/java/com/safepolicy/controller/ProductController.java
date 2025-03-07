package com.safepolicy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safepolicy.dao.ProductRepository;
import com.safepolicy.model.Product;

@EnableMethodSecurity
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

	@Autowired
	ProductRepository ProductRepository;
	
	@RequestMapping("/product")
	public String showProduct(){
		return "product";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(Product product){
		 ProductRepository.save(product);
		 return "home";
	}
}
