package net.endpoint.emailtemplate.dto;

import java.util.List;

public class EmailTemplateDto  {

	private String htmlContet;
	private String         name;
	private List<EmailTemplateAttachmentDto>   attachments;
	private List<EmailTemplateFieldDto>  fields;

	public EmailTemplateDto(String htmlContet, String name, List<EmailTemplateAttachmentDto> attachments,
			List<EmailTemplateFieldDto> fields) {
		super();
		this.htmlContet = htmlContet;
		this.name = name;
		this.attachments = attachments;
		this.fields = fields;
	}
	
	
	
	public String getHtmlContet() {
		return htmlContet;
	}
	public void setHtmlContet(String htmlContet) {
		this.htmlContet = htmlContet;
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
