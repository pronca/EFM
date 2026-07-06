package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;
import java.util.Date;

public class UploadReturnsRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String extractionId;
    private Date creationDate;
    private String tipoValidazioneReg;
    private Boolean hasErrors;
    private Date endProcessDate;
    
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

	public Boolean getHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(Boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Date getEndProcessDate() {
		return endProcessDate;
	}

	public void setEndProcessDate(Date endProcessDate) {
		this.endProcessDate = endProcessDate;
	}

    
}
