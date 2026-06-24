package it.eng.care.domain.flow.drools.model.status;

public class ValidationResultWrapper {

	private Boolean absentRules = false;

	private Boolean noErrors = false;

	private Boolean unvalidable = false;

	public ValidationResultWrapper() {
	}

	public Boolean getAbsentRules() {
		return absentRules;
	}

	public void setAbsentRules(Boolean absentRules) {
		this.absentRules = absentRules;
	}

	public Boolean getNoErrors() {
		return noErrors;
	}

	public void setNoErrors(Boolean noErrors) {
		this.noErrors = noErrors;
	}

	public Boolean getUnvalidable() {
		return unvalidable;
	}

	public void setUnvalidable(Boolean unvalidable) {
		this.unvalidable = unvalidable;
	}

}
