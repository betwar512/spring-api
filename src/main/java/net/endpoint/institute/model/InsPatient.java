package net.endpoint.institute.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import net.endpoint.account.model.Person;

@Entity
@Table(name="ins_patient")
public class InsPatient implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ins_patient_id")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=false)
	private Person person;
	@Column(name="refrence_uid")
	private String uid;
	
	
	public long getId() {
		return id;
	}
	public Person getPerson() {
		return person;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	

	
	
}
