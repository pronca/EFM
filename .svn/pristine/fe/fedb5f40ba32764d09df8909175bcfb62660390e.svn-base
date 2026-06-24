package it.eng.care.domain.flow.b2b.model;

public class FlowParameter {

    private int order;

    private String columnName;

    private Object value;

    private boolean primaryKey;
    
    private boolean dateRif;

    public FlowParameter(String columnName, Object value, int order, boolean primaryKey, boolean dateRif) {
        super();
        this.order = order;
        this.columnName = columnName;
        this.value = value;
        this.primaryKey = primaryKey;
        this.dateRif = dateRif;
    }

    public FlowParameter(String columnName, Object value, int order, boolean primaryKey) {
        super();
        this.order = order;
        this.columnName = columnName;
        this.value = value;
        this.primaryKey = primaryKey;
    }

    public String getParameterName() {
        return ":" + columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

	public boolean isDateRif() {
		return dateRif;
	}

	public void setDateRif(boolean dateRif) {
		this.dateRif = dateRif;
	}
    

}
