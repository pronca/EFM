package it.eng.care.domain.flow.b2b.model;

import java.util.List;

public class ErrorFieldsDTO {
    String section;
    String field;
    List<FieldErrorsDTO> fieldErrors;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<FieldErrorsDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorsDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
