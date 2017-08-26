package net.endpoint.emailtemplate.dto;

import java.util.Date;
import java.util.Locale;

class EmailDto {
	
	private String    from;
	private String      to;
	private String subject;
	private String content;
	private Locale  locale;
	private Date      timestamp;

	public EmailDto(String from,String to ,String subject, String content,Locale locale) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.timestamp = new Date();
		this.locale = locale!= null ? locale : new Locale("en", "AU");
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


	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	
	
}
