package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@Entity
@Table(name = "FM_FLOW_TABLE")
public class FlowTableDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SECTION")
    private Integer section;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FLOW")
    private FlowDO flowDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERSION")
    private VersionDO versionDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datasource")
    private DataSourceDO dataSource;

    @Lob
    @Column(name = "xsd")
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] xsd;

    @OneToMany(mappedBy = "idTable", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<FlowForeignKeyDO> table;

    @OneToMany(mappedBy = "idTableReferenced", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<FlowForeignKeyDO> tableReferenced;

    @OneToMany(mappedBy = "flowTable", fetch = FetchType.LAZY)
    private Set<FlowTableFieldDO> fields;
    
    @Column(name = "REQUIRED")
    private Boolean required;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public FlowDO getFlowDO() {
        return flowDO;
    }

    public void setFlowDO(FlowDO flowDO) {
        this.flowDO = flowDO;
    }

    public VersionDO getVersionDO() {
        return versionDO;
    }

    public void setVersionDO(VersionDO versionDO) {
        this.versionDO = versionDO;
    }

    public DataSourceDO getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceDO dataSource) {
        this.dataSource = dataSource;
    }

    public Set<FlowForeignKeyDO> getTable() {
        return table;
    }

    public void setTable(Set<FlowForeignKeyDO> table) {
        this.table = table;
    }

    public Set<FlowForeignKeyDO> getTableReferenced() {
        return tableReferenced;
    }

    public void setTableReferenced(Set<FlowForeignKeyDO> tableReferenced) {
        this.tableReferenced = tableReferenced;
    }

    public Set<FlowTableFieldDO> getFields() {
        return fields;
    }

    public void setFields(Set<FlowTableFieldDO> fields) {
        this.fields = fields;
    }

    public byte[] getXsd() {
        return xsd;
    }

    public void setXsd(byte[] xsd) {
        this.xsd = xsd;
    }

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}
    
    
}
