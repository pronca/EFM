package it.eng.care.domain.flow.core.dto.PrivacyManagerDTO;

import java.util.List;

public class PMFlowView {
	
	private List<Object> flowViewReturn;
	
	public PMFlowView() {
		
	}

	public List<Object> getFlowViewReturn() {
		return flowViewReturn;
	}

	public void setFlowViewReturn(List<Object> flowViewReturn) {
		this.flowViewReturn = flowViewReturn;
	}

}
