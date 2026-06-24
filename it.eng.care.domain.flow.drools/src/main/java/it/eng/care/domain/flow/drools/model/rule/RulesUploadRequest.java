package it.eng.care.domain.flow.drools.model.rule;

public class RulesUploadRequest extends RulesDownloadRequest {

	private static final long serialVersionUID = 2041208219127004605L;

	private String filename;

	private byte[] rule;

	public RulesUploadRequest() {
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getRule() {
		return rule;
	}

	public void setRule(byte[] rule) {
		this.rule = rule;
	}

}
