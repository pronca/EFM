package it.eng.care.domain.flow.drools.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name = "FM_VALIDATION_REQUEST_RESULT")
public class ValidationRequestResultDO {

	@Id
	@Column(name = "VR_ID")
	private String vrId;

	@Column(name = "IMPORTING_REQUEST_ID")
	private String importingRequestId;

	@Column(name = "ERRORS")
	private Integer errors;

	@Column(name = "WARNINGS")
	private Integer warnings;

	@Column(name = "VALIDS")
	private Integer valids;

	@Column(name = "VALIDATION_DATE")
	private Date validationDate;

	public String getVrId() {
		return vrId;
	}

	public void setVrId(String vrId) {
		this.vrId = vrId;
	}

	public String getImportingRequestId() {
		return importingRequestId;
	}

	public void setImportingRequestId(String importingRequestId) {
		this.importingRequestId = importingRequestId;
	}

	public Integer getErrors() {
		return errors;
	}

	public void setErrors(Integer errors) {
		this.errors = errors;
	}

	public Integer getWarnings() {
		return warnings;
	}

	public void setWarnings(Integer warnings) {
		this.warnings = warnings;
	}

	public Integer getValids() {
		return valids;
	}

	public void setValids(Integer valids) {
		this.valids = valids;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

}
