package net.endpoint.emailtemplate.dto;

import java.util.Date;
import java.util.Locale;

public class SendEmailDto extends EmailDto {
    private Date sendingDate;
	
	public SendEmailDto(String from, String to, String subject, String content, Locale locale,Date sendingDate) {
		super(from, to, subject, content, locale);
			this.sendingDate = sendingDate;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	
}
