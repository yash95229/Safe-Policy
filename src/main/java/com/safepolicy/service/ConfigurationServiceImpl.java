package com.safepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safepolicy.dao.ConfigurationRepository;
import com.safepolicy.model.Configuration;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{

	@Autowired
	ConfigurationRepository configurationRepository;
	
	@Override
	public Configuration save(Configuration configuration) {
		// TODO Auto-generated method stub
		return configurationRepository.save(configuration);
	}

	
}
