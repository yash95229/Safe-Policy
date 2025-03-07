package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.IncomeDetailRepository;
import com.safepolicy.model.IncomeDetail;

@Service
public class IncomeDetailServiceImpl implements IncomeDetailService {

	@Autowired
	IncomeDetailRepository incomeDetailRepository;
	
	@Override
	public IncomeDetail save(IncomeDetail incomeDetail) {
		// TODO Auto-generated method stub
		return incomeDetailRepository.save(incomeDetail) ;
	}

}
