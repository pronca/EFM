package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "FM_FLOW_CFG_FIELD_VALUE")
public class FlowConfigurationFilterFieldValueDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FILTER_FIELD")
    private FlowConfigurationFilterFieldDO configurationFilterField;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FlowConfigurationFilterFieldDO getConfigurationFilterField() {
        return configurationFilterField;
    }

    public void setConfigurationFilterField(FlowConfigurationFilterFieldDO configurationFilterField) {
        this.configurationFilterField = configurationFilterField;
    }

    public FlowExportRequestDO getFlowExportRequest() {
        return flowExportRequest;
    }

    public void setFlowExportRequest(FlowExportRequestDO flowExportRequest) {
        this.flowExportRequest = flowExportRequest;
    }
}
