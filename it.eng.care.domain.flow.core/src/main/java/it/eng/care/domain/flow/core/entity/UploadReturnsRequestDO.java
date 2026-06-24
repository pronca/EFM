package it.eng.care.domain.flow.core.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "FM_UPLOAD_RETURNS_REQUEST")
public class UploadReturnsRequestDO {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "EXTRACTION_ID")
	private String extractionId;

	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Lob()
	@Column(name = "RETURN_FILE")
	private byte[] file;

	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "TIPO_VALIDAZIONE_REG")
	private String tipoValidazioneReg;
	
	public UploadReturnsRequestDO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExtractionId() {
		return extractionId;
	}

	public void setExtractionId(String extractionId) {
		this.extractionId = extractionId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTipoValidazioneReg() {
		return tipoValidazioneReg;
	}

	public void setTipoValidazioneReg(String tipoValidazioneReg) {
		this.tipoValidazioneReg = tipoValidazioneReg;
	}
	
	
	
}
