package net.endpoint.account.model;

import java.util.Date;

public class AccountVerification {

	private long id;
	private String refrence;
	private String userName;
	private Date timestamp;
	
	
	public String generateCode() {
		return "CODE";
	}
	
	public boolean isExpierd() {
		return true;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRefrence() {
		return refrence;
	}
	public void setRefrence(String refrence) {
		this.refrence = refrence;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
