package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FM_FLOW_CONFIGURATION")
public class FlowConfigurationFilterDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "version")
    private String version;
    @Column(name = "flow")
    private String flow;
    //0 = DINAMIC; 1 = STATIC; 3 = STANDARD
    @Column(name = "type")
    private Integer type;
    @OneToMany(mappedBy = "configurationFilter", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<FlowConfigurationFilterFieldDO> filterFields;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "configurationFilters")
    private Set<FlowExportRequestDO> exportRequest;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Set<FlowConfigurationFilterFieldDO> getFilterFields() {
        return filterFields;
    }

    public void setFilterFields(Set<FlowConfigurationFilterFieldDO> filterFields) {
        this.filterFields = filterFields;
    }

    public Set<FlowExportRequestDO> getExportRequest() {
        return exportRequest;
    }

    public void setExportRequest(Set<FlowExportRequestDO> exportRequest) {
        this.exportRequest = exportRequest;
    }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }
}
