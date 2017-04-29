package net.endpoint.dto.account;

import net.endpoint.model.account.Phone;

public class PhoneDto {

	private String serverid;
	private String   number;
	
	public void pars(Phone phone){
		this.setNumber(phone.getNumber());
		this.setServerid(Long.toString(phone.getId()));
	}
	
	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid = serverid;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	
	
}
