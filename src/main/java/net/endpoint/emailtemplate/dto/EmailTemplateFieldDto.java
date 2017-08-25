package net.endpoint.emailtemplate.dto;

public class EmailTemplateFieldDto {

	private String name;
	private String filedId;
	private String content;
	
	
	
	public EmailTemplateFieldDto(String name, String filedId, String content) {
		super();
		this.name = name;
		this.filedId = filedId;
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
