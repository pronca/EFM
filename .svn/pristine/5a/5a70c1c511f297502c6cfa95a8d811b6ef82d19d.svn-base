package it.eng.care.domain.flow.core.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Entity
@Table(name = "FM_LOCKED_MESSAGE")
public class LockedMessageDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(
            name = "care-uuid",
            strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator"
    )
    @Column(name = "ID")
    private String id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "FLOW_ID")
    private FlowDO flowId;

    @Column(name = "FLOW_NAME")
    private String flowName;

    @Column(name = "EXTR_ID")
    private String extrId;

    @Column(name = "EXTRACTION_ID")
    private String extractionId;

    @Column(name = "KEY_MESSAGE")
    private String keyMessage;

    @Lob
    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DATE_PROCESSED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateProcessed;

    @Column(name = "CODICEAZIENDA")
    private String codiceAzienda;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public FlowDO getFlowId() {
        return flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public String getExtrId() {
        return extrId;
    }

    public String getExtractionId() {
        return extractionId;
    }

    public String getKeyMessage() {
        return keyMessage;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public String getCodiceAzienda() {
        return codiceAzienda;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setFlowId(FlowDO flowId) {
        this.flowId = flowId;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public void setExtrId(String extrId) {
        this.extrId = extrId;
    }

    public void setExtractionId(String extractionId) {
        this.extractionId = extractionId;
    }

    public void setKeyMessage(String keyMessage) {
        this.keyMessage = keyMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public void setCodiceAzienda(String codiceAzienda) {
        this.codiceAzienda = codiceAzienda;
    }
}