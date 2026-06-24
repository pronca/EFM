package it.eng.care.domain.flow.core.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DashboardFilterKey implements Serializable {

	public DashboardFilterKey() {
		
	}
	
	public DashboardFilterKey(String filterName, String dashBoard, String codiceAzienda) {
		this.filterName = filterName;
		this.dashBoard = dashBoard;
		this.codiceAzienda = codiceAzienda;
	}
	
	private static final long serialVersionUID = 1L;

	@Column(name = "FILTER_NAME", nullable = false)
    private String filterName;

	 @Column(name = "DASHBOARD")
	 private String dashBoard;
	 
	 @Column(name = "CODICEAZIENDA")
	 private String codiceAzienda;
	 
	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getDashBoard() {
		return dashBoard;
	}

	public void setDashBoard(String dashBoard) {
		this.dashBoard = dashBoard;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

}