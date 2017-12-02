package net.endpoint.emailtemplate.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;


class EmailDto {
	
	protected String       from;
	protected String         to;
	@Nullable
	protected List<String>  ccs;
	@Nullable
	protected List<String> bccs;
	protected String    subject;
	protected String    content;
	@Nullable
	protected Locale     locale;
	protected Date    timestamp;
	protected String   userName;

	
	public EmailDto() {}
	
	public EmailDto(String from,String to ,String subject, String content,String userName,Locale locale,List<String>  ccs,List<String>  bccs) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.timestamp = new Date();
		this.locale = locale!= null ? locale : new Locale("en", "AU");
		this.ccs  = ccs  != null ?  ccs : new ArrayList<>();
		this.bccs = bccs != null ? bccs : new ArrayList<>();
		this.userName = userName;
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

	public List<String> getCcs() {
		return ccs;
	}

	public List<String> getBccs() {
		return bccs;
	}

	public void setCcs(List<String> ccs) {
		this.ccs = ccs;
	}

	public void setBccs(List<String> bccs) {
		this.bccs = bccs;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
