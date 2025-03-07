package com.safepolicy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safepolicy.model.IncomeDetail;
import com.safepolicy.model.PersonalDetail;

public interface IncomeDetailRepository extends JpaRepository<IncomeDetail,Integer> {

	IncomeDetail findByQuoteId(String quoteId);
}
