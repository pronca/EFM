package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "FM_CONFIGURATION")
public class ConfigurationDO {
	
	
	@Id
    @Column(name = "KEY_ID")
    private String keyId;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @Column(name = "VALORE")
    private String value;

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}






}
