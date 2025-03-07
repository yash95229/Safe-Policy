package com.safepolicy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.safepolicy.model.PersonalDetail;

public interface PersonalDetailRepository extends JpaRepository<PersonalDetail, Long> {

	 @Query(value = "select nextval('personal_details_seq')" , nativeQuery = true)
	 long findTopByIdOrderByIdDesc();
	 
	 List<PersonalDetail> findByQuoteId(String quoteId);
}
