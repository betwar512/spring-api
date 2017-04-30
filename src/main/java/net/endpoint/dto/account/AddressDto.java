package net.endpoint.dto.account;

import net.endpoint.model.account.Address;

public class AddressDto {

	private String id;
	private String unit;
	private String address;
	
	
	public void pars(Address address){
		this.setAddress(address.getAddressLine1());
		this.setUnit(address.getNumber());
		this.setId(Long.toString(address.getId()));	
	}
	
	public Address convertTo(Address ad){
		  ad = ad!=null? ad : new Address();
		  if(!this.getAddress().isEmpty())
			  ad.setAddressLine1(this.getAddress());
		  if(!this.getUnit().isEmpty())
			  ad.setNumber(this.getUnit());
		  
		return ad;
	}
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
