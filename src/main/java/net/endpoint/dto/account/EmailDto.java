package net.endpoint.dto.account;

import java.util.Date;

public class EmailDto {
	
	private String from;
	private String subject;
	private String content;
	private Date date;

	public EmailDto(String from, String subject, String content,Date date) {
		super();
		this.from = from;
		this.subject = subject;
		this.content = content;
		this.date = date;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
	
}
