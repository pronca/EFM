package it.eng.care.domain.flow.core.dto.FlowView;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FlowViewFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private FormFlowDTO flow;
	private int firstResult;
	private int maxResult;
	private int firstResultAcc;
	private int maxResultAcc;
	private FlowViewFilterField[] field;
	private FlowViewFilterData[] fieldData;
	private String importId;
	private List<String> fkFields;
	private List<String> fkValues;
	private String rowStatus; // stato di validazione di una riga
	private String notRowStatus; // stato di validazione di una riga
	private boolean paginated = false;
	private String exported;
	private boolean count;
	private ImportTypeEnum importType;
	private String flowId;
	private String versionId;
	private String sortField;
	private String sortDir;
	private String sortSez;
	private String drg;
	private boolean scartiRegione = false;
	private String tipoImportazione;
	private String codicePresidio;
	private String codiceAzienda;
	private String dashboardName;
	
	// usato dal motore di validazione per inviare i risultati della validazione al
	// verticale
	private Boolean selectExtractionId = false;

	public FlowViewFilter() {
	}

	public FormFlowDTO getFlow() {
		return flow;
	}

	public void setFlow(FormFlowDTO flow) {
		this.flow = flow;
	}

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

	public FlowViewFilterData[] getFieldData() {
		return fieldData;
	}

	public void setFieldData(FlowViewFilterData[] fieldData) {
		this.fieldData = fieldData;
	}

	public List<String> getFkFields() {
		return fkFields;
	}

	public void setFkFields(List<String> fkFields) {
		this.fkFields = fkFields;
	}

	public List<String> getFkValues() {
		return fkValues;
	}

	public void setFkValues(List<String> fkValues) {
		this.fkValues = fkValues;
	}

	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public Boolean getSelectExtractionId() {
		return selectExtractionId;
	}

	public void setSelectExtractionId(Boolean selectExtractionId) {
		this.selectExtractionId = selectExtractionId;
	}

	public boolean isPaginated() {
		return paginated;
	}

	public void setPaginated(boolean paginated) {
		this.paginated = paginated;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getFirstResultAcc() {
		return firstResultAcc;
	}

	public void setFirstResultAcc(int firstResultAcc) {
		this.firstResultAcc = firstResultAcc;
	}

	public int getMaxResultAcc() {
		return maxResultAcc;
	}

	public void setMaxResultAcc(int maxResultAcc) {
		this.maxResultAcc = maxResultAcc;
	}

	public String getExported() {
		return exported;
	}

	public void setExported(String exported) {
		this.exported = exported;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	public ImportTypeEnum getImportType() {
		return importType;
	}

	public void setImportType(ImportTypeEnum importType) {
		this.importType = importType;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getNotRowStatus() {
		return notRowStatus;
	}

	public void setNotRowStatus(String notRowStatus) {
		this.notRowStatus = notRowStatus;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getSortSez() {
		return sortSez;
	}

	public void setSortSez(String sortSez) {
		this.sortSez = sortSez;
	}

	public String getDrg() {
		return drg;
	}

	public void setDrg(String drg) {
		this.drg = drg;
	}

	public boolean isScartiRegione() {
		return scartiRegione;
	}

	public void setScartiRegione(boolean scartiRegione) {
		this.scartiRegione = scartiRegione;
	}

	public String getTipoImportazione() {
		return tipoImportazione;
	}

	public void setTipoImportazione(String tipoImportazione) {
		this.tipoImportazione = tipoImportazione;
	}

	public String getCodicePresidio() {
		return codicePresidio;
	}

	public void setCodicePresidio(String codicePresidio) {
		this.codicePresidio = codicePresidio;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}
	
	public String getDashboardName() {
		return dashboardName;
	}

	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}
}
