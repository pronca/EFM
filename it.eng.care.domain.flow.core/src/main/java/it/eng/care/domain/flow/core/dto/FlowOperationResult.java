package it.eng.care.domain.flow.core.dto;

import java.util.List;

public class FlowOperationResult<T> {

	private Boolean success;

	private String message;

	private List<String> messages;

	private T result;
	
	private String downloadFileName;
	private String downloadContentType;
	private String downloadBase64;
	
	public static <T> FlowOperationResult<T> success() {
		FlowOperationResult<T> res = new FlowOperationResult<T>();
		res.setSuccess(true);
		return res;
	}

	public static <T> FlowOperationResult<T> success(T result) {
		FlowOperationResult<T> res = new FlowOperationResult<T>();
		res.setSuccess(true);
		res.setResult(result);
		return res;
	}
	

	public static <T> FlowOperationResult<T> failure() {
		FlowOperationResult<T> res = new FlowOperationResult<>();
		res.setSuccess(false);
		res.setMessage("Operazione fallita");
		return res;
	}

	public static <T> FlowOperationResult<T> failure(String message) {
		FlowOperationResult<T> res = new FlowOperationResult<>();
		res.setSuccess(false);
		res.setMessage(message);
		return res;
	}

	public static <T> FlowOperationResult<T> failure(List<String> messages) {
		FlowOperationResult<T> res = new FlowOperationResult<>();
		res.setSuccess(false);
		res.setMessages(messages);
		return res;
	}
	
	public static <T> FlowOperationResult<T> failure(String message, String fileName, String contentType, String base64) {
	    FlowOperationResult<T> res = new FlowOperationResult<>();
	    res.setSuccess(false);
	    res.setMessage(message);
	    res.setDownloadFileName(fileName);
	    res.setDownloadContentType(contentType);
	    res.setDownloadBase64(base64);
	    return res;
	}
	
	public static <T> FlowOperationResult<T> failure(String message, List<String> messages, String fileName, String contentType, String base64) {
	    FlowOperationResult<T> res = new FlowOperationResult<>();
	    res.setSuccess(false);
	    res.setMessage(message);
	    res.setMessages(messages);
	    res.setDownloadFileName(fileName);
	    res.setDownloadContentType(contentType);
	    res.setDownloadBase64(base64);
	    return res;
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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public String getDownloadFileName() {
	    return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
	    this.downloadFileName = downloadFileName;
	}

	public String getDownloadContentType() {
	    return downloadContentType;
	}

	public void setDownloadContentType(String downloadContentType) {
	    this.downloadContentType = downloadContentType;
	}

	public String getDownloadBase64() {
	    return downloadBase64;
	}

	public void setDownloadBase64(String downloadBase64) {
	    this.downloadBase64 = downloadBase64;
	}
}
