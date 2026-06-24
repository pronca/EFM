package it.eng.care.domain.flow.b2b.model;

public class LockedMessageDTO {
	private String id;
    private String flowId;
    private String flowName;
    private String keyMessage;
    private String message;
    private String extrId;
    private String extractionId;

    public LockedMessageDTO() {
    }

    public LockedMessageDTO(String id, String flowId, String flowName, String keyMessage, String message, String extrId) {
        this.id = id;
        this.flowId = flowId;
        this.flowName = flowName;
        this.keyMessage = keyMessage;
        this.message = message;
        this.extrId = extrId;
    }

    public String getId() {
        return id;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public String getKeyMessage() {
        return keyMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getExtrId() {
        return extrId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public void setKeyMessage(String keyMessage) {
        this.keyMessage = keyMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExtrId(String extrId) {
        this.extrId = extrId;
    }

	public String getExtractionId() {
		return extractionId;
	}

	public void setExtractionId(String extractionId) {
		this.extractionId = extractionId;
	}
    
    
}
