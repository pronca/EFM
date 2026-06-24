package it.eng.care.domain.flow.core.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import it.eng.care.platform.persistence.api.IBaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_DASHBOARD")
public class DashboardDO implements IBaseEntity {

    @Id
    @GeneratedValue(
            generator = "care-uuid"
    )
    @GenericGenerator(
            name = "care-uuid",
            strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator"
    )
    @Column(name = "ID")
    private String id;
    @Column(name = "FLOW")
    private String flow;
    @Column(name = "CODICEAZIENDA")
    private String codiceAzienda;
    @Column(name = "MONTH")
    private Integer month;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "LABEL")
    private String label;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "WIDGET_NAME")
    private String widgetName;
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;
    @Column(name = "TOOLTIP")
    private String tooltip;
    @Column(name = "DAY")
    private Date day;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "DASHBOARD", nullable = false, insertable = false, updatable = false)
	private Set<DashboardFilterDO> dashboardFilter = new HashSet<DashboardFilterDO>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }


    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public Set<DashboardFilterDO> getDashboardFilter() {
		return dashboardFilter;
	}

	public void setDashboardPraticheFilter(Set<DashboardFilterDO> dashboardFilter) {
		this.dashboardFilter = dashboardFilter;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
	
}
