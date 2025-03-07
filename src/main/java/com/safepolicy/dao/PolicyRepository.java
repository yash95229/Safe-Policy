package com.safepolicy.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.safepolicy.model.Policy;


public interface PolicyRepository extends JpaRepository<Policy, Long> {

	List<Policy> findByQuoteId(String quoteId);
	
	Page<Policy> findByUserId(long userId, Pageable pageable);
	List<Policy> findByUserId(long userId);
}
