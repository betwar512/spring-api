package net.endpoint.emailtemplate.dto;

import java.util.ArrayList;
import java.util.List;

public class EmailTemplateDto  {

	private String  htmlContent;
	private String         name;
	private List<EmailTemplateAttachmentDto>   attachments;
	private List<EmailTemplateFieldDto>  fields;

	public EmailTemplateDto(String htmlContet, String name, List<EmailTemplateAttachmentDto> attachments,
			List<EmailTemplateFieldDto> fields) {
		super();
		this.htmlContent = htmlContet;
		this.name = name;
		this.attachments = attachments != null ? attachments : new ArrayList<>();
		this.fields = fields != null ? fields : new ArrayList<>();
	}
	
	
	
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContet) {
		this.htmlContent = htmlContet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EmailTemplateAttachmentDto> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<EmailTemplateAttachmentDto> attachments) {
		this.attachments = attachments;
	}
	
	public List<EmailTemplateFieldDto> getFields() {
		return fields;
	}
	public void setFields(List<EmailTemplateFieldDto> fields) {
		this.fields = fields;
	}
	
 }
