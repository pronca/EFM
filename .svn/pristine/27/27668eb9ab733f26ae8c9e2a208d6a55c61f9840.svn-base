package it.eng.care.domain.flow.drools.utility.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.drools.model.lookup.ColumnType;
import it.eng.care.domain.flow.drools.service.api.UtilityService;
import it.eng.care.domain.flow.drools.utility.api.DroolsQueryUtility;

public class DroolsQueryUtilityImpl implements DroolsQueryUtility {
	
	@Autowired
	private UtilityService service;
	
	@Override
	public List<Object> executeQuery(String sql, Object[] parameters, ColumnType[] type) {
		return service.executeQuery(sql, parameters, type);
	}

	@Override
	public Object executeUniqueQuery(String sql, Object[] parameters, ColumnType[] type) {
		return service.executeUniqueQuery(sql, parameters, type);
	}

	@Override
	public Integer executeCount(String sql, Object[] parameters, ColumnType[] type) {
		return service.executeCount(sql, parameters, type);
	}

	@Override
	public Boolean executeExists(String sql, Object[] parameters, ColumnType[] type) {
		return service.executeExists(sql, parameters, type);
	}

}
