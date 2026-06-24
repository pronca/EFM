package it.eng.care.domain.flow.core.dto;

import java.util.List;


public class ExportDataDTO {

	private String versionSql;
	private String  flowSql;
	private List<String>flowTableSql;
	private List<String>flowTableFieldsSql;
	private List<String>flowTableForeignFieldsSql;
	private List<String>createTableSectionAndMessagesSql;
	private List<String>insertFlowVersionSql;
	
	private FlowVersionDTO flowVersionDto;
	private ProfiloFlussiDTO profiloFlussiDto;
	
	public ExportDataDTO() {}

	public String getVersionSql() {
		return versionSql;
	}

	public void setVersionSql(String versionSql) {
		this.versionSql = versionSql;
	}

	public String getFlowSql() {
		return flowSql;
	}

	public void setFlowSql(String flowSql) {
		this.flowSql = flowSql;
	}

	public List<String> getFlowTableSql() {
		return flowTableSql;
	}

	public void setFlowTableSql(List<String> flowTableSql) {
		this.flowTableSql = flowTableSql;
	}

	public List<String> getFlowTableFieldsSql() {
		return flowTableFieldsSql;
	}

	public void setFlowTableFieldsSql(List<String> flowTableFieldsSql) {
		this.flowTableFieldsSql = flowTableFieldsSql;
	}

	public FlowVersionDTO getFlowVersionDto() {
		return flowVersionDto;
	}

	public void setFlowVersionDto(FlowVersionDTO flowVersionDto) {
		this.flowVersionDto = flowVersionDto;
	}

	public List<String> getFlowTableForeignFieldsSql() {
		return flowTableForeignFieldsSql;
	}

	public void setFlowTableForeignFieldsSql(List<String> flowTableForeignFieldsSql) {
		this.flowTableForeignFieldsSql = flowTableForeignFieldsSql;
	}
		
	public List<String> getCreateTableSectionAndMessagesSql() {
		return createTableSectionAndMessagesSql;
	}
	
	public void setCreateTableSectionAndMessagesSql(List<String> createTableSectionAndMessagesSql) {
		this.createTableSectionAndMessagesSql = createTableSectionAndMessagesSql;
	}
	
	public List<String> getInsertFlowVersionSql() {
		return insertFlowVersionSql;
	}
	
	public void setInsertFlowVersionSql(List<String> insertFlowVersionSql) {
		this.insertFlowVersionSql = insertFlowVersionSql;
	}

	public ProfiloFlussiDTO getProfiloFlussiDto() {
		return profiloFlussiDto;
	}

	public void setProfiloFlussiDto(ProfiloFlussiDTO profiloFlussiDto) {
		this.profiloFlussiDto = profiloFlussiDto;
	}
	
}
