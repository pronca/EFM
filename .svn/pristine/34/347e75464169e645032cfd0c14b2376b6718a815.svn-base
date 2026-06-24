package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Entity
@Table(name = "FM_SEQUENCE")
public class FmSequenceDO implements IBaseEntity {
	
	@Id
	@Column(name = "ENTITY")
	private String id;

	@Column(name = "CURRENT_VALUE")
	private Integer currentValue;


	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

}
