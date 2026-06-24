package it.eng.care.domain.flow.drools.model.rule;

import java.io.Serializable;
import java.util.List;

public class RulesUploadResponse implements Serializable {

	private static final long serialVersionUID = -1728361722776015553L;

	private Boolean success;

	private String message;

	private List<String> messages;

	public RulesUploadResponse() {
		this.success = true;
	}
	
	public RulesUploadResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public RulesUploadResponse(Boolean success, List<String> messages) {
		super();
		this.success = success;
		this.messages = messages;
	}
	
	public static RulesUploadResponse success() {
		return new RulesUploadResponse(true, "OK");
	}
	
	public static RulesUploadResponse failure(String message) {
		return new RulesUploadResponse(false, message);
	}
	
	public static RulesUploadResponse failure(List<String> messages) {
		return new RulesUploadResponse(false, messages);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
