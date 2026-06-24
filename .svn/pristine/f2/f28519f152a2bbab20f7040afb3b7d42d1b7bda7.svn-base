package it.eng.care.domain.flow.b2b.utils;

import it.eng.care.domain.flow.b2b.model.ErrorWellFormedDTO;
import it.eng.care.domain.flow.b2b.model.ErrorsWFDTO;
import it.eng.care.domain.flow.b2b.model.FlowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FlowOperationResult {


    private static Logger logger = LoggerFactory.getLogger(FlowOperationResult.class);


    public ErrorWellFormedDTO success(FlowResult flow) {

        ErrorWellFormedDTO wellformed = new ErrorWellFormedDTO();

        wellformed.setSuccess(true);
        wellformed.setOpTargetObject(flow.getId());
        return wellformed;

    }

    public ErrorWellFormedDTO failure(List<String> exceptions) {

        ErrorWellFormedDTO wellformed = new ErrorWellFormedDTO();

        wellformed.setSuccess(false);
        List<ErrorsWFDTO> errors = new ArrayList<>();
        for (String exception : exceptions) {
            ErrorsWFDTO error = new ErrorsWFDTO();
            error.setText(exception);
            error.setType("ERROR");
            error.setPath(null);
            error.setTopLevel(true);
            errors.add(error);

        }
        wellformed.setErrors(errors);

        return wellformed;

    }
    
    public ErrorWellFormedDTO failure(List<String> exceptions, String errorCode) {
        return failure(exceptions, errorCode, null);
    }

    public ErrorWellFormedDTO failure(List<String> exceptions, String errorCode, String opTargetObject) {

        ErrorWellFormedDTO wellformed = new ErrorWellFormedDTO();

        if (opTargetObject != null && !opTargetObject.isBlank()) {
            wellformed.setOpTargetObject(opTargetObject);
        }

        if (errorCode != null) {
            if (errorCode.equals("FM_ERR_99")) {
                wellformed.setSuccess(true);

                List<ErrorsWFDTO> warnings = new ArrayList<>();
                ErrorsWFDTO warning = new ErrorsWFDTO();
                warning.setText("La scheda è in stato consolidato e verrà elaborata dal Gestore flussi in una fase successiva");
                warning.setType("WARNING");
                warning.setPath(null);
                warning.setTopLevel(true);
                warnings.add(warning);

                wellformed.setWarnings(warnings);
            } else {
                wellformed.setSuccess(false);
                List<ErrorsWFDTO> errors = new ArrayList<>();
                for (String exception : exceptions) {
                    ErrorsWFDTO error = new ErrorsWFDTO();
                    error.setText(exception);
                    error.setType("ERROR");
                    error.setPath(null);
                    error.setTopLevel(true);
                    errors.add(error);
                }
                wellformed.setErrors(errors);
            }
        } else {
            return failure(exceptions);
        }

        return wellformed;
    }

}
