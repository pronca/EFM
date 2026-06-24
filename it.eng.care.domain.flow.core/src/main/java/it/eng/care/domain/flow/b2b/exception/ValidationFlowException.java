package it.eng.care.domain.flow.b2b.exception;

import java.util.List;

public class ValidationFlowException extends Exception {

    private static final long serialVersionUID = 1L;

    private List<String> errors;

    public ValidationFlowException(List<String> errors) {
        super();
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
