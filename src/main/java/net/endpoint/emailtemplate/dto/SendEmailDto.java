package net.endpoint.emailtemplate.dto;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SendEmailDto extends EmailDto {
    private Date sendingDate;
    private String password;
    
    public SendEmailDto() {}
	
	public SendEmailDto(String from, String to,
			String subject, String content,String userName,String password, 
			Locale locale,Date sendingDate,
			List<String>  ccs,List<String>  bccs) {
		super(from, to, subject, content,userName, locale,ccs,bccs);
			this.sendingDate = sendingDate;
			this.password = password;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
