package net.endpoint.account.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.endpoint.account.model.Address;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.Phone;

/**
 * 
 * @author Betwar
 *
 */
public class ProfileDto {

	private String  username;
	private String firstname;
	private String  lastname;
	private String     email;
	private Date         dob;
	private String lastLogin;
	private List<AddressDto> addresses = new ArrayList<>();
	private List<PhoneDto>      phones = new ArrayList<>();
	private Date timestamp ;
	
	public ProfileDto(){
		this.setTimestamp(new Date());
		}
	
	/**
	 * Pars Model to Dto 
	 * @param person
	 */
	public void pars(Person person){	
		if(person != null){
		this.username  = person.getUser().getUserName();
		this.email     = person.getUser().getEmail();
		this.firstname = person.getFirstName();
		this.lastname  = person.getLastName();
		this.dob       = person.getDateOfBirth();
		this.lastLogin = new Date().toString();
	if(!person.getAddresses().isEmpty()){
		for(Address a:person.getAddresses()){
			AddressDto adDto = new AddressDto();
			adDto.pars(a);
			addresses.add(adDto);
			}
	  }
	
	if(!person.getPhones().isEmpty()){
		for(Phone p :person.getPhones()){
			PhoneDto phdto = new PhoneDto();
			phdto.pars(p);
			phones.add(phdto);
		 }
	   }
	  }
	}
	
	
	public String getFullName() {
		return this.firstname + " " + this.lastname;
	}
	
	
	public Date getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}


	public List<AddressDto> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}


	public List<PhoneDto> getPhones() {
		return phones;
	}


	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "ProfileDto [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", dob=" + dob + ", lastLogin=" + lastLogin + ", addresses=" + addresses + ", phones="
				+ phones + ", timestamp=" + timestamp + "]";
	}
	

	
	
	
	
	
	

}
