package it.eng.care.domain.flow.core.dto.PrivacyManagerDTO;

import java.util.List;

import it.eng.care.domain.flow.core.dto.PaginatedPraticaDTO;

public class PMPraticaView {
	
	private PaginatedPraticaDTO praticaViewReturns;
	private List<Object[]> queryResult;
	
	public PMPraticaView() {
	}

	public PaginatedPraticaDTO getPraticaViewReturns() {
		return praticaViewReturns;
	}

	public void setPraticaViewReturns(PaginatedPraticaDTO praticaViewReturns) {
		this.praticaViewReturns = praticaViewReturns;
	}

	public List<Object[]> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(List<Object[]> queryResult) {
		this.queryResult = queryResult;
	}

}
