package it.eng.care.domain.flow.core.dto;

import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;

import java.io.Serializable;

public class ValidationFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String flowId;
    private String flowType;
    private String versionId;
//    private String[] param; // valore campi
    private FlowViewFilterField[] field; // definizione campi filtro
    private String importId; // id della richiesta di importazione dal verticale
    private String rowStatus; // stato di validazione di una riga
    private String notRowStatus; // stato di validazione di una riga
    private String exported;
	private ImportTypeEnum importType;
	
	private String month, year;

    public ValidationFilter() {
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
//
//    public String[] getParam() {
//        return param;
//    }
//
//    public void setParam(String[] param) {
//        this.param = param;
//    }

    public FlowViewFilterField[] getField() {
        return field;
    }

    public void setField(FlowViewFilterField[] field) {
        this.field = field;
    }

    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }

    public String getExported() {
        return exported;
    }

    public void setExported(String exported) {
        this.exported = exported;
    }

	public ImportTypeEnum getImportType() {
		return importType;
	}

	public void setImportType(ImportTypeEnum importType) {
		this.importType = importType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNotRowStatus() {
		return notRowStatus;
	}

	public void setNotRowStatus(String notRowStatus) {
		this.notRowStatus = notRowStatus;
	}

	

}
