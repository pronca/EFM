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

import org.hibernate.annotations.GenericGenerator;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Entity
@Table(name = "FM_REGION_ERRORS")
public class RegionErrorsDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;

    @Lob
    @Column(name = "JSON")
    private String json;

    @Column(name = "SEND_STATUS")
    private String sendStatus;

    @Column(name = "REGION_STATUS")
    private String regionStatus;

    @Column(name = "NUM_RETRY")
    private Integer nretry;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "CODICEAZIENDA")
    private String codiceAzienda;

    @ManyToOne
    @JoinColumn(name = "FLOW_ID")
    private FlowDO flowId;

    @Column(name = "EXTR_ID")
    private String extrId;

    @Column(name = "DATE_SENT")
    private Date dateSent;
    
    @Column(name = "KEY_MESSAGE")
    private String keyMessage;
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getRegionStatus() {
        return regionStatus;
    }

    public void setRegionStatus(String regionStatus) {
        this.regionStatus = regionStatus;
    }

    public Integer getNretry() {
        return nretry;
    }

    public void setNretry(Integer nretry) {
        this.nretry = nretry;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCodiceAzienda() {
        return codiceAzienda;
    }

    public void setCodiceAzienda(String codiceAzienda) {
        this.codiceAzienda = codiceAzienda;
    }

    public FlowDO getFlowId() {
        return flowId;
    }

    public void setFlowId(FlowDO flowId) {
        this.flowId = flowId;
    }

    public String getExtrId() {
        return extrId;
    }

    public void setExtrId(String extrId) {
        this.extrId = extrId;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

	public String getKeyMessage() {
		return keyMessage;
	}

	public void setKeyMessage(String keyMessage) {
		this.keyMessage = keyMessage;
	}
    
    
}