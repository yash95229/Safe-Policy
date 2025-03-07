package com.safepolicy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safepolicy.model.AddonBenefits;
import com.safepolicy.model.IncomeDetail;

public interface AddonBenefitsRepository extends JpaRepository<AddonBenefits, Integer> {

	List<AddonBenefits> findByQuoteId(String quoteId);
}
