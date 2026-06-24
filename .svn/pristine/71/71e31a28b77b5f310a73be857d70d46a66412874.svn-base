package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "FM_FLOW_IMPORT_REQUEST_FIELD")
public class FlowImportRequestFieldDateDO implements IBaseEntity {
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
    @Column(name = "ID_FIELD")
    private String id_field;
    @Column(name = "DATE_TO")
    private Date date_to;
    @Column(name = "DATE_FROM")
    private Date date_from;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private FlowImportRequestDO flowImportRequest;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getId_field() {
        return id_field;
    }

    public void setId_field(String id_field) {
        this.id_field = id_field;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public FlowImportRequestDO getFlowImportRequest() {
        return flowImportRequest;
    }

    public void setFlowImportRequest(FlowImportRequestDO flowImportRequest) {
        this.flowImportRequest = flowImportRequest;
    }
}

