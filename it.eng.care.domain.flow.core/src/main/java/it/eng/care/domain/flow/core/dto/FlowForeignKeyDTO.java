package it.eng.care.domain.flow.core.dto;

import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;

import java.io.Serializable;

public class FlowForeignKeyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private FlowTableDO idTable;
    private FlowTableDO idTableReferenced;
    private FlowTableFieldDO idFieldTable;
    private FlowTableFieldDO idFieldTableReferenced;
    private String jsonField;
    private String jsonFieldType;
    private boolean mandatory;

    public FlowForeignKeyDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlowTableDO getIdTable() {
        return idTable;
    }

    public void setIdTable(FlowTableDO idTable) {
        this.idTable = idTable;
    }

    public FlowTableDO getIdTableReferenced() {
        return idTableReferenced;
    }

    public void setIdTableReferenced(FlowTableDO idTableReferenced) {
        this.idTableReferenced = idTableReferenced;
    }

    public FlowTableFieldDO getIdFieldTable() {
        return idFieldTable;
    }

    public void setIdFieldTable(FlowTableFieldDO idFieldTable) {
        this.idFieldTable = idFieldTable;
    }

    public FlowTableFieldDO getIdFieldTableReferenced() {
        return idFieldTableReferenced;
    }

    public void setIdFieldTableReferenced(FlowTableFieldDO idFieldTableReferenced) {
        this.idFieldTableReferenced = idFieldTableReferenced;
    }

    public String getJsonField() {
        return jsonField;
    }

    public void setJsonField(String jsonField) {
        this.jsonField = jsonField;
    }

    public String getJsonFieldType() {
        return jsonFieldType;
    }

    public void setJsonFieldType(String jsonFieldType) {
        this.jsonFieldType = jsonFieldType;
    }
}
