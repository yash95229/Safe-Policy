package com.safepolicy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.safepolicy.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
}
