package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class FlowTableDataSourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private FlowTableDTO flowTable;
    private DataSourceDTO dataSource;

    public FlowTableDataSourceDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlowTableDTO getFlowTable() {
        return flowTable;
    }

    public void setFlowTable(FlowTableDTO flowTable) {
        this.flowTable = flowTable;
    }

    public DataSourceDTO getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceDTO dataSource) {
        this.dataSource = dataSource;
    }
}
