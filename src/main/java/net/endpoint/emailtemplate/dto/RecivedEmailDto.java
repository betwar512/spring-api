package net.endpoint.emailtemplate.dto;

import java.util.Date;

public class RecivedEmailDto extends EmailDto{

	
	private Date recivedDate;

	
	public RecivedEmailDto(String from, String to, String subject, String content,Date recivedDate) {
		super(from, to, subject, content, null,null,null);
		this.recivedDate = recivedDate;
	}


	public Date getRecivedDate() {
		return recivedDate;
	}

	public void setRecivedDate(Date recivedDate) {
		this.recivedDate = recivedDate;
	}
	
	
}
