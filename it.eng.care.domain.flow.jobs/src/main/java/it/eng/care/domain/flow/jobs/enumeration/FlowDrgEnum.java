package it.eng.care.domain.flow.jobs.enumeration;

public enum FlowDrgEnum {
	
	RICHIESTA("RICHIESTA"), IN_CORSO("IN_CORSO"), OK("OK"), KO("BEN KO");
	
	private String description;
	
	private FlowDrgEnum(String descr) {
		this.description = descr;
	}

	public String getDescription() {
		return description;
	}

}
