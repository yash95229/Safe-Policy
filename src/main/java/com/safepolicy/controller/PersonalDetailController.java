package com.safepolicy.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safepolicy.dao.ConfigurationRepository;
import com.safepolicy.dao.PersonalDetailRepository;
import com.safepolicy.dao.ProductRepository;
import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.Configuration;
import com.safepolicy.model.PersonalDetail;
import com.safepolicy.model.Product;
import com.safepolicy.model.User;
import com.safepolicy.util.GeneralUtil;


@EnableMethodSecurity
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class PersonalDetailController {

	@Autowired
	PersonalDetailRepository personalDetailRepository;
	
	@Autowired
	ConfigurationRepository configurationRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping("/personalDetail")
	public String regPage(Model model) {
		List<Configuration> configData =  configurationRepository.findAll();
		
		List<Configuration> configFilterData = configData.stream().filter(entry -> entry.getAnnualIncome() != null && !entry.getAnnualIncome().isEmpty())
	.collect(Collectors.toList());
		
		model.addAttribute("ConfigurationData", configFilterData);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String username = userDetails.getUsername();
		
		User currentUserData = userRepository.findByUsername(username);
		
		if (currentUserData == null) { 
            return "redirect:/login"; 
        }
		
		String fullName = currentUserData.getFullName();
		String number = currentUserData.getNumber();
		String email = currentUserData.getUsername();
		Date date = currentUserData.getDob();
		String gender = currentUserData.getGender();
		String aadhaarNumber = currentUserData.getAadhaarNumber();
		String panNumber = currentUserData.getPanNumber();
	
		model.addAttribute("fullName", fullName);
		model.addAttribute("number", number);
		model.addAttribute("dob", date);
		model.addAttribute("email", email);
		model.addAttribute("gender", gender);
		model.addAttribute("aadhaarNumber", aadhaarNumber);
		model.addAttribute("panNumber", panNumber);
		
		
	
		return "personalDetail";
	}
	
	@PostMapping("/addPersonalDetail") 	
	public String addPersonalDetails(PersonalDetail personalDetail,Model model) {
	
//		Long personalDetailId = personalDetailRepository.findTopByIdOrderByIdDesc();
//		String quoteId = GeneralUtil.getNumberingFormat(personalDetailId+1);
//		personalDetail.setQuoteId(quoteId);
		
		
		Long personalDetailId;
	    
	    try {
	        personalDetailId = personalDetailRepository.findTopByIdOrderByIdDesc();
	    } catch (Exception e) {
	        // Handle exception due to empty table or sequence not existing
	        personalDetailId = 1L; // or any default starting value
	    }
	    
	    if (personalDetailId == null) {
	        personalDetailId = 1L; // or any default starting value
	    }
	    
	    String quoteId = GeneralUtil.getNumberingFormat(personalDetailId + 1);
	    personalDetail.setQuoteId(quoteId);
		personalDetailRepository.save(personalDetail);
		
		Configuration configAnnualIncome = configurationRepository.findByAnnualIncome(personalDetail.getAnnualIncome());
		model.addAttribute("sumAssured",  configAnnualIncome.getSumAssured());
		
		List<Product> productData = productRepository.findAll();
		model.addAttribute("productData", productData);
		
		model.addAttribute("quoteId", quoteId);
					return "incomeDetail";
	}
	
	
}
