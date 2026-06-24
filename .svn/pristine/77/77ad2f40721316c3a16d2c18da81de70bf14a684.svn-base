package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Table(name = "FM_MONITOR_SDO_XL_FILE")
@Entity
public class MonitorSdoXlFileDO implements IBaseEntity{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "ID_FLUSSO")
	private String idFlusso;
	
	@Column(name = "PRESIDIO")
	private String presidio;
	
	@Column(name = "NOSOLOGICO")
	private String nosologico;
	
	@Lob
	@Column(name = "DATA_FILE")	
	private byte[] xml;
	
	@Column(name = "ID_ESTRAZIONE")
	private String idEstrazione;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}
	public String getPresidio() {
		return presidio;
	}
	public void setPresidio(String presidio) {
		this.presidio = presidio;
	}
	public String getNosologico() {
		return nosologico;
	}
	public void setNosologico(String nosologico) {
		this.nosologico = nosologico;
	}
	public byte[] getXml() {
		return xml;
	}
	public void setXml(byte[] xml) {
		this.xml = xml;
	}
	public String getIdEstrazione() {
		return idEstrazione;
	}
	public void setIdEstrazione(String idEstrazione) {
		this.idEstrazione = idEstrazione;
	}
	
}
