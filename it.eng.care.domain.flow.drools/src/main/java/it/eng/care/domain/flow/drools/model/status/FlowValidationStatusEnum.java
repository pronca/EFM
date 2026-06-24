package it.eng.care.domain.flow.drools.model.status;

/**
 * Stati del processo di validazione di un flusso
 *
 */
public enum FlowValidationStatusEnum {

	VALIDATING("IN VALIDAZIONE"), //
	VALIDATED_KO("VALIDAZIONE FALLITA"), //
	VALIDATING_OK("VALIDATO"), //
	VALIDATING_ERROR("VALIDATO CON ERRORI"), //
	VALIDATING_WARNING("VALIDATO CON WARNING"), //
	VALIDATING_ERROR_WARNING("VALIDATO CON ERRORI E WARNING");

	private String description;

	private FlowValidationStatusEnum(String descr) {
		this.description = descr;
	}

	public String getDescription() {
		return description;
	}

}
