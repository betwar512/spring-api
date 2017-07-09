package net.endpoint.dto.account;

import net.endpoint.model.account.Phone;

public class PhoneDto {

	public String serverid;
	public String   number;
	public String     type;
	
	public void pars(Phone phone){
		this.setNumber(phone.getNumber());
		this.setServerid(Long.toString(phone.getId()));
		this.type = phone.getType();
	}
	
	/**
	 * 
	 * @param phone null for new object 
	 * @return
	 */
	public Phone convertTo(Phone phone){
		phone = phone!=null ? phone : new Phone();
	   if(!this.getNumber().isEmpty()){
		   phone.setNumber(this.getNumber());
	       }
		   		phone.setCountryCode("+61");   
		return phone;
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
