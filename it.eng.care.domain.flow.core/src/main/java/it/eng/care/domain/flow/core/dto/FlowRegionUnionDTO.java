package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class FlowRegionUnionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private FlowDTO flowLocal;
    private FlowDTO flowRegion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlowDTO getFlowLocal() {
        return flowLocal;
    }

    public void setFlowLocal(FlowDTO flowLocal) {
        this.flowLocal = flowLocal;
    }

    public FlowDTO getFlowRegion() {
        return flowRegion;
    }

    public void setFlowRegion(FlowDTO flowRegion) {
        this.flowRegion = flowRegion;
    }
}