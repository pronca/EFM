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
@Table(name = "FM_DASHBOARD_PRATICHE")
public class DashboardPraticheDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "FLOW_NAME")
    private String flowName;
    
    @Column(name = "CODICEAZIENDA")
    private String codiceAzienda;
    
    @Column(name = "DAY1")
    private Integer day1;

    @Column(name = "DAY2")
    private Integer day2;

    @Column(name = "DAY3")
    private Integer day3;

    @Column(name = "DAY4")
    private Integer day4;

    @Column(name = "DAY5")
    private Integer day5;

    @Column(name = "DAY6")
    private Integer day6;

    @Column(name = "DAY7")
    private Integer day7;

    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "DASHBOARD", nullable = false, insertable = false, updatable = false)
	private Set<DashboardPraticheFilterDO> dashboardPraticheFilter = new HashSet<DashboardPraticheFilterDO>();

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

    public Integer getDay1() {
        return day1;
    }

    public void setDay1(Integer day1) {
        this.day1 = day1;
    }

    public Integer getDay2() {
        return day2;
    }

    public void setDay2(Integer day2) {
        this.day2 = day2;
    }

    public Integer getDay3() {
        return day3;
    }

    public void setDay3(Integer day3) {
        this.day3 = day3;
    }

    public Integer getDay4() {
        return day4;
    }

    public void setDay4(Integer day4) {
        this.day4 = day4;
    }

    public Integer getDay5() {
        return day5;
    }

    public void setDay5(Integer day5) {
        this.day5 = day5;
    }

    public Integer getDay6() {
        return day6;
    }

    public void setDay6(Integer day6) {
        this.day6 = day6;
    }

    public Integer getDay7() {
        return day7;
    }

    public void setDay7(Integer day7) {
        this.day7 = day7;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

	public Set<DashboardPraticheFilterDO> getDashboardPraticheFilter() {
		return dashboardPraticheFilter;
	}

	public void setDashboardPraticheFilter(Set<DashboardPraticheFilterDO> dashboardPraticheFilter) {
		this.dashboardPraticheFilter = dashboardPraticheFilter;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}
    
    

}
