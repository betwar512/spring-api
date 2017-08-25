package net.endpoint.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="phone")
public class Phone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum PHONE_TYPE{
		Home,Office;
	}
	
	@Id
	private long            id;
	
	private String        type;
	@Column(name="phone_number")
	private String      number;
	@Column(name="country_code")
	private String countryCode;
	
	private Date     timestamp;
	@ManyToOne
	@JoinColumn(name="person_id",nullable=false)
	private Person      person;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
}
