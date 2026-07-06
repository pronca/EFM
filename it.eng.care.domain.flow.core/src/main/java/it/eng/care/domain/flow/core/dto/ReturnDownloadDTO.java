package it.eng.care.domain.flow.core.dto;

public class ReturnDownloadDTO {
	private String extractionId;
    private String fileName;
    private String contentBase64;
    private Boolean available;
    private String flowId;
    
	public String getExtractionId() {
		return extractionId;
	}
	public void setExtractionId(String extractionId) {
		this.extractionId = extractionId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentBase64() {
		return contentBase64;
	}
	public void setContentBase64(String contentBase64) {
		this.contentBase64 = contentBase64;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
    
    
    
}
