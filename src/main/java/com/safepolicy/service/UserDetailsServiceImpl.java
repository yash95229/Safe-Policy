package com.safepolicy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsername(username);
		
		if(user==null) 
			throw new UsernameNotFoundException("User Not Found");
		
//		UserDetailsImpl  UserDetailsImpl = new UserDetailsImpl(user.get(0));
		
		return new UserDetailsImpl(user);
	}

}
