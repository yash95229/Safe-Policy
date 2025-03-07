package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.PolicyRepository;
import com.safepolicy.model.Policy;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;
	
	@Override
	public Policy save(Policy policy) {
		// TODO Auto-generated method stub
		return policyRepository.save(policy) ;
	}

}
