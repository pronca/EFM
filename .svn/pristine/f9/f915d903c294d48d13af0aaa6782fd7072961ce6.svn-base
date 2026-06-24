package it.eng.care.domain.flow.core.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import it.eng.care.platform.persistence.api.IBaseEntity;

@Entity
@Table(name = "FM_DASHBOARD_ERRORS")
public class DashboardErrorsDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "FLOW_NAME")
    private String flowName;
    
    @Column(name = "CODICEAZIENDA")
    private String codiceAzienda;

    @Column(name = "ERROR")
    private String error;

    @Column(name = "COUNT_ERROR")
    private Integer countError;

    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "YEAR")
    private String year;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "DAY")
    private Date day;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "DASHBOARD", nullable = false, insertable = false, updatable = false)
	private Set<DashboardErrorsFilterDO> dashboardErrorsFilter = new HashSet<DashboardErrorsFilterDO>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getCountError() {
        return countError;
    }

    public void setCountError(Integer countError) {
        this.countError = countError;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<DashboardErrorsFilterDO> getDashboardErrorsFilter() {
		return dashboardErrorsFilter;
	}

	public void setDashboardPraticheFilter(Set<DashboardErrorsFilterDO> dashboardErrorsFilter) {
		this.dashboardErrorsFilter = dashboardErrorsFilter;
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
