package net.endpoint.emailtemplate.dto;


public class EmailTemplateAttachmentDto {

	private String name;
	private String htmlFiledId;
	private byte[] attachedFile;
	private String fileType;

	public EmailTemplateAttachmentDto(String name, String htmlFiledId, String fileType, byte[] attachedFile) {
		super();
		this.name = name;
		this.htmlFiledId = htmlFiledId;
		this.attachedFile = attachedFile;
		this.fileType = fileType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtmlFiledId() {
		return htmlFiledId;
	}

	public void setHtmlFiledId(String htmlFiledId) {
		this.htmlFiledId = htmlFiledId;
	}

	public byte[] getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(byte[] attachedFile) {
		this.attachedFile = attachedFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
