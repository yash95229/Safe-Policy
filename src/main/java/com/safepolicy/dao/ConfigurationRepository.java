package com.safepolicy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safepolicy.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

	 Configuration findByAnnualIncome(String annualIncome);
	
}
