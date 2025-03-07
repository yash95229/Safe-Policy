package com.safepolicy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.safepolicy.dao.ConfigurationRepository;
import com.safepolicy.model.Configuration;
import com.safepolicy.model.Product;


@EnableMethodSecurity
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ConfigurationController {

	@Autowired
	ConfigurationRepository configurationRepository;
	
	@RequestMapping("/configuration")
	public String viewConfiguration() {
		return "configuration";
	}
	
	@PostMapping("/addConfiguration")
	public String addConfigurationSumAssured(Configuration configuration){
		
		configurationRepository.save(configuration);
		
		 return "home";
	} 
	
	
	
}
