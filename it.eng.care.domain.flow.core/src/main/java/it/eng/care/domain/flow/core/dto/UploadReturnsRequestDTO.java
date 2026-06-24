package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;
import java.util.Date;

public class UploadReturnsRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String extractionId;
    private Date creationDate;
    private String tipoValidazioneReg;
    
    public UploadReturnsRequestDTO() {
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

	public String getTipoValidazioneReg() {
		return tipoValidazioneReg;
	}

	public void setTipoValidazioneReg(String tipoValidazioneReg) {
		this.tipoValidazioneReg = tipoValidazioneReg;
	}

    
}
