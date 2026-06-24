package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "FM_FLOW_FOREIGNKEY")
public class FlowForeignKeyDO
        implements IBaseEntity {

    @Id
    @GeneratedValue(
            generator = "care-uuid"
    )
    @GenericGenerator(
            name = "care-uuid",
            strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator"
    )
    @Column(name = "ID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_table")
    private FlowTableDO idTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_field_table")
    private FlowTableFieldDO idFieldTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_table_referenced")
    private FlowTableDO idTableReferenced;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_field_table_referenced")
    private FlowTableFieldDO idFieldTableReferenced;

    @Column(name = "json_field")
    private String jsonField;

    @Column(name = "json_field_type")
    private String jsonFieldType;

    @Column(name = "mandatory")
    private Boolean mandatory;

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

    public Boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }
}
