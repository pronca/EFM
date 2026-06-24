package it.eng.care.domain.flow.core.dto;

import java.util.Map;

public class ExtractionErrorMessage {

    private String extractionId;

    private String errorDescription;

    private String fieldName;

    private String fieldValue;

    private String severity;

    private String sectionName;

    private Map<String, Object> keys;

    public String getExtractionId() {
        return extractionId;
    }

    public void setExtractionId(String extractionId) {
        this.extractionId = extractionId;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Map<String, Object> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, Object> keys) {
        this.keys = keys;
    }

}
