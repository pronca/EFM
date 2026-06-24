package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "FM_FLOW_CONFIGURATION_FIELD")
public class FlowConfigurationFilterFieldDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RANGE")
    private boolean range;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "FILTERTYPE")
    private String filterType;

    @Column(name = "FILTERTABLE")
    private String filterTable;

    @Column(name = "FILTERFIELD")
    private String filterField;

    @Column(name = "MANDATORY")
    private boolean mandatory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FILTER_CONFIGURATION")
    private FlowConfigurationFilterDO configurationFilter;
//
//    @OneToMany(mappedBy = "configurationFilterField", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
//    private Set<FlowConfigurationFilterFieldValueDO> filterValues;


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

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterTable() {
        return filterTable;
    }

    public void setFilterTable(String filterTable) {
        this.filterTable = filterTable;
    }

    public String getFilterField() {
        return filterField;
    }

    public void setFilterField(String filterField) {
        this.filterField = filterField;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public FlowConfigurationFilterDO getConfigurationFilter() {
        return configurationFilter;
    }

    public void setConfigurationFilter(FlowConfigurationFilterDO configurationFilter) {
        this.configurationFilter = configurationFilter;
    }
//
//    public Set<FlowConfigurationFilterFieldValueDO> getFilterValues() {
//        return filterValues;
//    }
//
//    public void setFilterValues(Set<FlowConfigurationFilterFieldValueDO> filterValues) {
//        this.filterValues = filterValues;
//    }
}
