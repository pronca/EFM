package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_DASHBOARD_PRATICHE_FILTER_VALUE")
public class DashboardPraticheFilterDO {

	public DashboardPraticheFilterDO() {
		
	}
	
	public DashboardPraticheFilterDO(String filterName, String filterValue, String dashboard, String codiceAzienda) {
		this.key = new PraticheFilterKey(filterName, dashboard, codiceAzienda);
		this.filterValue = filterValue;
	}
	
	public DashboardPraticheFilterDO(PraticheFilterKey key, String filterValue) {
		this.key = key;
		this.filterValue = filterValue;
	}

	@EmbeddedId
    private PraticheFilterKey key;
    
	@Column(name = "FILTER_VALUE", nullable = false)
    private String filterValue;

	public PraticheFilterKey getKey() {
		return key;
	}

	public void setKey(PraticheFilterKey key) {
		this.key = key;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

}
