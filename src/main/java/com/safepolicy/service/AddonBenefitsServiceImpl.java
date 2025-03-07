package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.AddonBenefitsRepository;
import com.safepolicy.model.AddonBenefits;

@Service
public class AddonBenefitsServiceImpl implements AddonBenefitsService{

	@Autowired
	AddonBenefitsRepository adonBenefitsRepository;
	
	@Override
	public AddonBenefits save(AddonBenefits addonBenefits) {
		// TODO Auto-generated method stub
		return adonBenefitsRepository.save(addonBenefits);
	}

}
