package com.safepolicy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.User;

@Controller
public class LoginController {


	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Principal principal, Model model) {
		
		System.out.println("in login controller::::");
		String username = null;
		if (principal != null) {
		    username = principal.getName();
		}

		User currentUserData = null;
		if (username != null) {
		    currentUserData = userRepository.findByUsername(username);
		}

		if (currentUserData != null) {
		    model.addAttribute("currentUser", currentUserData.getFullName());
		} else {
		    model.addAttribute("currentUser", "Guest"); 
		}

	
		return "home";
	}
	
	
	@RequestMapping("/login")
	public String loginPage(){

		return "login";
	}
	
	
}
