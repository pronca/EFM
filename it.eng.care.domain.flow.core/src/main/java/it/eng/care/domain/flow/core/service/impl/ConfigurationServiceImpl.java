package it.eng.care.domain.flow.core.service.impl;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;
import it.eng.care.domain.flow.core.service.ConfigurationService;

@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {
	
	@Autowired
	private ConfigurationDAO configurationDAO;

	@Override
	public void saveByKeyId(ConfigurationDO conf) {

		configurationDAO.save(conf);
		
	}

}
