package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "FM_FLOW_REG_UNION")
public class FlowRegionUnionDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOW_LOC")
    private FlowDO flowLocal;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOW_REG")
    private FlowDO flowRegion;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public FlowDO getFlowLocal() {
        return flowLocal;
    }

    public void setFlowLocal(FlowDO flowLocal) {
        this.flowLocal = flowLocal;
    }

    public FlowDO getFlowRegion() {
        return flowRegion;
    }

    public void setFlowRegion(FlowDO flowRegion) {
        this.flowRegion = flowRegion;
    }
}
	
