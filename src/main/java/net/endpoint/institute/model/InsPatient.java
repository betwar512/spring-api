package net.endpoint.institute.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import net.endpoint.account.model.Person;

public class InsPatient implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ins_patient_id")
	private long id;
	@OneToOne
	@JoinColumn(name="person_id",nullable=false)
	private Person person;
	
}
