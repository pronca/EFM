package it.eng.care.domain.flow.drools.model.row;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.eng.care.domain.flow.drools.model.status.BeanValidationStatusEnum;

/**
 * Bean da inviare al motore di validazione
 *
 */
public class ValidationBean {

	private List<Section> sections;

	private Set<DroolsError> errors;

	private String extractionId; // id della richiesta di ricezione della pratica

	private Date referenceDate; // data di riferimento della pratica
	
	private String codiceAzienda; // codice azienda della pratica per gestire la multiazienda su unico flusso
	
	private Map<String, Object> rowGroupMap = new HashMap<String, Object>(); // campi pratica con valore
	
	private BeanValidationStatusEnum validationStatus = BeanValidationStatusEnum.VALID;

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Set<DroolsError> getErrors() {
		return errors;
	}

	public void setErrors(Set<DroolsError> errors) {
		this.errors = errors;
	}

	public void addErrorMessage(DroolsError error) {
		if (errors == null) {
			errors = new HashSet<DroolsError>();
		}
		errors.add(error);
	}

	/**
	 * Restituisce la sezione con nome {sectionName}
	 * 
	 * @param sectionName
	 * @return
	 */
	public Section getSection(String sectionName) {
		Section foundedSection = null;
		if (sections != null && !sections.isEmpty()) {
			for (Section section : sections) {
				if (section.getName().equalsIgnoreCase(sectionName)) {
					foundedSection = section;
					break;
				}
			}
		}

		return foundedSection;
	}

	/**
	 * Restituisce la sezione con indice {sectionIndex}
	 * 
	 * @param sectionIndex
	 * @return
	 */
	public Section getSection(Integer sectionIndex) {
		Section foundedSection = null;
		if (sections != null && !sections.isEmpty() && sectionIndex != null) {
			for (Section section : sections) {
				if (section.getIndex().equals(sectionIndex)) {
					foundedSection = section;
					break;
				}
			}
		}

		return foundedSection;
	}

	public BeanValidationStatusEnum getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(BeanValidationStatusEnum validationStatus) {
		this.validationStatus = validationStatus;
	}

	public String getExtractionId() {
		return extractionId;
	}

	public void setExtractionId(String extractionId) {
		this.extractionId = extractionId;
	}

	public Date getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
	}

	public Map<String, Object> getRowGroupMap() {
		return rowGroupMap;
	}

	public void setRowGroupMap(Map<String, Object> rowGroupMap) {
		this.rowGroupMap = rowGroupMap;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}
	
	
}
