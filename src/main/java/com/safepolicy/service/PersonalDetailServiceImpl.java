package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.PersonalDetailRepository;
import com.safepolicy.model.PersonalDetail;

@Service
public class PersonalDetailServiceImpl implements PersonalDetailService{

	@Autowired
	PersonalDetailRepository personalDetailRepository;

	@Override
	public PersonalDetail save(PersonalDetail personalDetail) {
		// TODO Auto-generated method stub
		return personalDetailRepository.save(personalDetail);
	}
}
