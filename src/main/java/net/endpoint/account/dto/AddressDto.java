package net.endpoint.account.dto;

import net.endpoint.account.model.Address;

public class AddressDto {

	public String id;
	public String unit;
	public String addressline1;
	public String addressline2;
	public String state;
	public String country;
	public String city;
	public Integer postCode;

	/**
	 * 
	 * @param address
	 */
	public void pars(Address address) {
		this.addressline1 = address.getAddressLine1();
		this.addressline2 = address.getAddressLine2();
		this.unit    = address.getUnit();
		this.country = address.getCountry();
		this.city    = address.getCity();
		this.state   = address.getState();
		this.id      = String.valueOf(address.getId());
		this.postCode = address.getPostCode();
	}

	/**
	 * 
	 * @param ad
	 * @return
	 */
	public Address convertTo(Address ad) {
		ad = ad != null ? ad : new Address();
		ad.setAddressLine1(this.addressline1);
		ad.setUnit(this.unit);
		ad.setAddressLine2(this.addressline2);
		ad.setCity(this.city);
		ad.setPostCode(this.postCode);
		ad.setCountry(this.country);
		return ad;
	}

	@Override
	public String toString() {
		return "AddressDto [id=" + id + ", unit=" + unit + ", addressline1=" + addressline1 + ", addressline2="
				+ addressline2 + ", state=" + state + ", country=" + country + ", city=" + city + ", postCode="
				+ postCode + "]";
	}
	
	
	

}
