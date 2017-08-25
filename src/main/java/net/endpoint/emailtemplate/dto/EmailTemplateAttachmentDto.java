package net.endpoint.emailtemplate.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmailTemplateAttachmentDto {

	private String name;
	private String htmlFiledId;
	private MultipartFile  attachedFile;
	
	public EmailTemplateAttachmentDto(String name, String htmlFiledId, MultipartFile attachedFile) {
		super();
		this.name = name;
		this.htmlFiledId = htmlFiledId;
		this.attachedFile = attachedFile;
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
	public MultipartFile getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(MultipartFile attachedFile) {
		this.attachedFile = attachedFile;
	}

}
