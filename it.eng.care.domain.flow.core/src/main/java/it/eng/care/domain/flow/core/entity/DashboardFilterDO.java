package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_DASHBOARD_FILTER_VALUE")
public class DashboardFilterDO {

	public DashboardFilterDO() {
		
	}
	
	public DashboardFilterDO(String filterName, String filterValue, String dashboard, String codiceAzienda) {
		this.key = new DashboardFilterKey(filterName, dashboard, codiceAzienda);
		this.filterValue = filterValue;
	}
	
	public DashboardFilterDO(DashboardFilterKey key, String filterValue) {
		this.key = key;
		this.filterValue = filterValue;
	}
	
	@EmbeddedId
    private DashboardFilterKey key;
    
	@Column(name = "FILTER_VALUE", nullable = false)
    private String filterValue;

	public String getFilterName() {
		return this.getKey().getFilterName();
	}

	public void setFilterName(String filterName) {
		this.getKey().setFilterName(filterName);
	}

	public String getFilterValue() {
		return this.filterValue;
	}

	public void setFilterValue(String filterValue) {
		 this.filterValue = filterValue;
	}

	public DashboardFilterKey getKey() {
		return key;
	}

	public void setKey(DashboardFilterKey key) {
		this.key = key;
	}
	
	public String getDashBoard() {
		return this.getKey().getDashBoard();
	}

	public void setDashBoard(String dashBoard) {
		this.getKey().setDashBoard(dashBoard);
	}

    

}
