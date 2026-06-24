package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Entity
@Table(name = "FM_FLOW_EXP_ERR_DETAILS")
public class FlowExportMessageDetailsDO implements IBaseEntity{


	    @Id
	    @GeneratedValue(generator = "care-uuid")
	    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
	    @Column(name = "ID")
	    private String id;

	    @Column(name = "ERROR_VALUE")
	    private String errorValue;

	   

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "ID_EXPORT_REQ")
	    private FlowExportRequestDO flowExportRequest;


	    @Override
	    public String getId() {
	        return id;
	    }

	    @Override
	    public void setId(String id) {
	        this.id = id;
	    }

		public String getErrorValue() {
			return errorValue;
		}

		public void setErrorValue(String errorValue) {
			this.errorValue = errorValue;
		}

		public FlowExportRequestDO getFlowExportRequest() {
			return flowExportRequest;
		}

		public void setFlowExportRequest(FlowExportRequestDO flowExportRequest) {
			this.flowExportRequest = flowExportRequest;
		}

}