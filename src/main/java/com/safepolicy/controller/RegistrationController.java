package com.safepolicy.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.User;
import com.safepolicy.service.UserService;


@Controller
public class RegistrationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user) {
      
    	 long userCount = userRepository.count();
	       
	        if (userCount == 0) {
	            user.setRole("ADMIN");
	        } else {
	            user.setRole("USER");
	        }
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
        return "redirect:/login";
    }
  
}
