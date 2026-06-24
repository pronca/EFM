package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "FM_FLOW_IMPORTING_REQUEST")
public class FlowImportRequestDO implements IBaseEntity {
    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOW")
    private FlowDO flow;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERSION")
    private VersionDO version;
    @OneToMany(mappedBy = "flowImportRequest", fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL}, orphanRemoval = true)
    private Set<FlowImportRequestFieldDateDO> dateFields;
    @Column(name = "IMPORTING_USER")
    private String importingUser;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "RECORD")
    private String record;
    @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Column(name = "START_EXTRACTION_DATE")
    private Date startExtractionDate;
    @Column(name = "END_EXTRACTION_DATE")
    private Date endExtractionDate;

    @Column(name = "VALIDATION_STATUS")
    private String validationStatus;
    
    @Column(name = "AZIENDEPROFILOFLUSSI")
    private String aziendeProfiloFlussi;
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public FlowDO getFlow() {
        return flow;
    }

    public void setFlow(FlowDO flow) {
        this.flow = flow;
    }

    public VersionDO getVersion() {
        return version;
    }

    public void setVersion(VersionDO version) {
        this.version = version;
    }

    public Set<FlowImportRequestFieldDateDO> getDateFields() {
        return dateFields;
    }

    public void setDateFields(Set<FlowImportRequestFieldDateDO> dateFields) {
        this.dateFields = dateFields;
    }

    public String getImportingUser() {
        return importingUser;
    }

    public void setImportingUser(String importingUser) {
        this.importingUser = importingUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getStartExtractionDate() {
        return startExtractionDate;
    }

    public void setStartExtractionDate(Date startExtractionDate) {
        this.startExtractionDate = startExtractionDate;
    }

    public Date getEndExtractionDate() {
        return endExtractionDate;
    }

    public void setEndExtractionDate(Date endExtractionDate) {
        this.endExtractionDate = endExtractionDate;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

	public String getAziendeProfiloFlussi() {
		return aziendeProfiloFlussi;
	}

	public void setAziendeProfiloFlussi(String aziendeProfiloFlussi) {
		this.aziendeProfiloFlussi = aziendeProfiloFlussi;
	}
    
    
}
