package it.eng.care.domain.flow.core.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "FM_FLOW_EXPORTING_REQUEST")
public class FlowExportRequestDO implements IBaseEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "RANDOM_ID")
    private String randomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOW")
    private FlowDO flow;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERSION")
    private VersionDO version;
    @Column(name = "REQUESTER")
    private String requester;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_TALEND_ID")
    private JobTalendDO jobTalendId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fm_flow_config_export",
            joinColumns = {@JoinColumn(name = "id_export")},
            inverseJoinColumns = {@JoinColumn(name = "id_conf")})
    private Set<FlowConfigurationFilterDO> configurationFilters;
    @Column(name = "SCHEDULING_TYPE")
    private String schedulingType;

    @Column(name = "SCHEDULING_STARTING_TIME")
    private Date schedulingStartingTime;

    @Column(name = "SCHEDULING_NEXT_TIME")
    private Date schedulingNextTime;

    @Column(name = "SCHEDULING_INTERVAL")
    private String schedulingInterval;
    
    @Column(name = "SCHEDULING_INTERVAL_MINUTES")
    private Integer schedulingIntervalMinutes;
    
    @Column(name = "SCHEDULING_INTERVAL_SECONDS")
    private Integer schedulingIntervalSeconds;
    
    @Lob
    @Column(name = "file_talend")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String fileTalend;
    @Column(name = "VALIDATION_STATUS")
    private String validationStatus;
    @Column(name = "VALIDATION_STATUS_DRL")
    private String validationStatusDrl;
    @Column(name = "CONSOLIDATA")
    private Boolean consolidata;
    @Column(name = "DATE_CONS")
    private Date dateCons;
    @Column(name = "USER_CONS")
    private String userCons;
    @Column(name = "DELETED")
    private Integer deleted;
    @Column(name = "DRG")
    private Boolean drg;

    @OneToMany(mappedBy = "flowExportRequest", cascade = {CascadeType.ALL})
    private Set<FlowExportMessageDetailsDO> flowExportMessageDetails;

    @OneToMany(mappedBy = "flowExportRequest", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<FlowConfigurationFilterFieldValueDO> values;
    
    @Column(name = "REGION_VALIDATION_STATUS")
    private String regionValidationStatus;
    
    @OneToMany(mappedBy = "extractionId", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<UploadReturnsRequestDO> uploadReturnRequest;
    
    @Column(name = "AZIENDEPROFILOFLUSSI")
    private String aziendeProfiloFlussi;

    public Set<FlowConfigurationFilterFieldValueDO> getValues() {
        return values;
    }

    public void setValues(Set<FlowConfigurationFilterFieldValueDO> values) {
        this.values = values;
    }

    @Override
    public String getId() {
        return id;
    }

    public Set<UploadReturnsRequestDO> getUploadReturnRequest() {
		return uploadReturnRequest;
	}

	public void setUploadReturnRequest(Set<UploadReturnsRequestDO> uploadReturnRequest) {
		this.uploadReturnRequest = uploadReturnRequest;
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

    public JobTalendDO getJobTalendId() {
        return jobTalendId;
    }

    public void setJobTalendId(JobTalendDO jobTalendId) {
        this.jobTalendId = jobTalendId;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public Set<FlowConfigurationFilterDO> getConfigurationFilters() {
        return configurationFilters;
    }

    public void setConfigurationFilters(Set<FlowConfigurationFilterDO> configurationFilters) {
        this.configurationFilters = configurationFilters;
    }

    public String getFileTalend() {
        return fileTalend;
    }

    public void setFileTalend(String fileTalend) {
        this.fileTalend = fileTalend;
    }

    public String getSchedulingType() {
        return schedulingType;
    }

    public void setSchedulingType(String schedulingType) {
        this.schedulingType = schedulingType;
    }

    public Date getSchedulingStartingTime() {
        return schedulingStartingTime;
    }

    public void setSchedulingStartingTime(Date schedulingStartingTime) {
        this.schedulingStartingTime = schedulingStartingTime;
    }

    public String getSchedulingInterval() {
        return schedulingInterval;
    }

    public void setSchedulingInterval(String schedulingInterval) {
        this.schedulingInterval = schedulingInterval;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public Date getSchedulingNextTime() {
        return schedulingNextTime;
    }

    public void setSchedulingNextTime(Date schedulingNextTime) {
        this.schedulingNextTime = schedulingNextTime;
    }

    public String getRandomId() {
        return randomId;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }

	public Boolean getConsolidata() {
		return consolidata;
	}

	public void setConsolidata(Boolean consolidata) {
		this.consolidata = consolidata;
	}

    public Date getDateCons() {
        return dateCons;
    }

    public void setDateCons(Date dateCons) {
        this.dateCons = dateCons;
    }

    public String getUserCons() {
        return userCons;
    }

    public void setUserCons(String userCons) {
        this.userCons = userCons;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    
    public Boolean isDrg() {
        return drg;
    }

    public void setDrg(Boolean drg) {
        this.drg = drg;
    }

	public Set<FlowExportMessageDetailsDO> getFlowExportMessageDetails() {
		return flowExportMessageDetails;
	}

	public void setFlowExportMessageDetails(Set<FlowExportMessageDetailsDO> flowExportMessageDetails) {
		this.flowExportMessageDetails = flowExportMessageDetails;
	}

	public String getRegionValidationStatus() {
		return regionValidationStatus;
	}

	public void setRegionValidationStatus(String regionValidationStatus) {
		this.regionValidationStatus = regionValidationStatus;
	}

	public String getValidationStatusDrl() {
		return validationStatusDrl;
	}

	public void setValidationStatusDrl(String validationStatusDrl) {
		this.validationStatusDrl = validationStatusDrl;
	}

	public String getAziendeProfiloFlussi() {
		return aziendeProfiloFlussi;
	}

	public void setAziendeProfiloFlussi(String aziendeProfiloFlussi) {
		this.aziendeProfiloFlussi = aziendeProfiloFlussi;
	}
	
	public Integer getSchedulingIntervalMinutes() {
	    return schedulingIntervalMinutes;
	}

	public void setSchedulingIntervalMinutes(Integer schedulingIntervalMinutes) {
	    this.schedulingIntervalMinutes = schedulingIntervalMinutes;
	}
	
	public Integer getSchedulingIntervalSeconds() {
	    return schedulingIntervalSeconds;
	}

	public void setSchedulingIntervalSeconds(Integer schedulingIntervalSeconds) {
	    this.schedulingIntervalSeconds = schedulingIntervalSeconds;
	}
	
	
}
	
