package it.eng.care.domain.flow.drools.model.status;

import java.util.ArrayList;
import java.util.List;

import it.eng.care.domain.flow.drools.model.row.DroolsError;

/**
 * Esito della validazione di un flusso
 *
 */
public class ValidationResult {

	private Integer warnings = 0;

	private Integer errors = 0;

	private Integer valids = 0;
	
	private List<DroolsError> droolsError;
	
	private String extractionId;

	public ValidationResult() {
		droolsError = new ArrayList<DroolsError>();
	}

	public Integer getWarnings() {
		return warnings;
	}

	public void setWarnings(Integer warnings) {
		this.warnings = warnings;
	}

	public Integer getErrors() {
		return errors;
	}

	public void setErrors(Integer errors) {
		this.errors = errors;
	}

	public Integer getValids() {
		return valids;
	}

	public void setValids(Integer valids) {
		this.valids = valids;
	}

	public void incrementErrors(BeanValidationStatusEnum statusEnum, Integer increment) {
		if (statusEnum == null) {
			this.valids += increment;
		} else if (BeanValidationStatusEnum.WARNING.equals(statusEnum)) {
			this.warnings += increment;
		} else if (BeanValidationStatusEnum.ERROR.equals(statusEnum)) {
			this.errors += increment;
		} else {
			this.valids += increment;
		}
	}

	public String calculateValidationStatus() {
		String status = FlowValidationStatusEnum.VALIDATING_OK.name();

		if (errors != 0 || warnings != 0) {
			if (warnings == 0) {
				status = FlowValidationStatusEnum.VALIDATING_ERROR.name();
			} else if (errors == 0) {
				status = FlowValidationStatusEnum.VALIDATING_WARNING.name();
			} else {
				status = FlowValidationStatusEnum.VALIDATING_ERROR_WARNING.name();
			}
		}

		return status;
	}

	public List<DroolsError> getDroolsError() {
		return droolsError;
	}

	public void setDroolsError(List<DroolsError> droolsError) {
		this.droolsError = droolsError;
	}

	public String getExtractionId() {
		return extractionId;
	}

	public void setExtractionId(String extractionId) {
		this.extractionId = extractionId;
	}
	
}
