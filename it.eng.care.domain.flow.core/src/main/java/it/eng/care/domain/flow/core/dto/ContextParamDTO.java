package it.eng.care.domain.flow.core.dto;

import java.util.HashMap;

public class ContextParamDTO {
	
	private HashMap<String,String> params;
	private String activity;
	private boolean disableDefaultLanding;
	
	public ContextParamDTO() {
		
	}
	
	public HashMap<String,String> getParams() {
		return params;
	}
	public void setParams(HashMap<String,String> params) {
		this.params = params;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

	public boolean isDisableDefaultLanding() {
		return disableDefaultLanding;
	}

	public void setDisableDefaultLanding(boolean disableDefaultLanding) {
		this.disableDefaultLanding = disableDefaultLanding;
	}
	
	

}
