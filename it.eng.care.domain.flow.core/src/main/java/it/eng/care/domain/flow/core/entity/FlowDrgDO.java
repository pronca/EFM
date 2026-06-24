package it.eng.care.domain.flow.core.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Entity
@Table(name = "FM_FLOW_DRG")
public class FlowDrgDO implements IBaseEntity{

    @Id
    @GeneratedValue(generator = "care-uuid")
    @Column(name = "ID")
    private String id;
    
    @OneToOne()
    @JoinColumn(name = "EXTRACTION_ID")
    private FlowExportRequestDO extraction;

    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.DATE)
    private Date sendDate;

    @Column(name = "RETURN_DATE")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column(name = "ERROR")
    private String error;
    
    @Column(name = "STATE")
    private String state;
    
    @Column(name = "NUM_PRATICHE")
    private String numPratiche;

    @Override
    public String getId() {
		return id;
	}

    @Override
	public void setId(String id) {
		this.id = id;
	}

	public FlowExportRequestDO getExtraction() {
		return extraction;
	}

	public void setExtraction(FlowExportRequestDO extraction) {
		this.extraction = extraction;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNumPratiche() {
		return numPratiche;
	}

	public void setNumPratiche(String numPratiche) {
		this.numPratiche = numPratiche;
	}
    
}
