package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FlowVersionPrimaryKeyDO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "flow")
    protected String flow;
    @Column(name = "version")
    protected String version;

    public FlowVersionPrimaryKeyDO(String flow, String version) {
        super();
        this.flow = flow;
        this.version = version;
    }

    public FlowVersionPrimaryKeyDO() {
        super();
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
