package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.AddonBenefitsRepository;
import com.safepolicy.model.AddonBenefits;


public interface AddonBenefitsService {

	public AddonBenefits save(AddonBenefits addonBenefits);
	
	
}
