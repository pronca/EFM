package it.eng.care.domain.flow.core.dto;

public class DownloadFileDTO {
	private String fileName;
    private String contentType;
    private String base64Content;

    public DownloadFileDTO() {
    }

    public DownloadFileDTO(String fileName, String contentType, String base64Content) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.base64Content = base64Content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBase64Content() {
        return base64Content;
    }

    public void setBase64Content(String base64Content) {
        this.base64Content = base64Content;
    }
}
