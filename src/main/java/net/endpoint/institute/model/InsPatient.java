package net.endpoint.institute.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy="patient")
	private Set<InsAnatomy> anatomies = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="person_id",nullable=false)
	private Person person;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="ins_practitioner_patient" , joinColumns={
	@JoinColumn(name="ins_patient_id",nullable=false,updatable=false)},
	inverseJoinColumns={
	@JoinColumn(name="ins_practitioner_id",nullable=false,updatable=false)})
	private Set<InsPractitioner> practitioners = new HashSet<>();
	
	
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
	public Set<InsAnatomy> getAnatomies() {
		return anatomies;
	}
	public Set<InsPractitioner> getPractitioners() {
		return practitioners;
	}
	public void setAnatomies(Set<InsAnatomy> anatomies) {
		this.anatomies = anatomies;
	}
	public void setPractitioners(Set<InsPractitioner> practitioners) {
		this.practitioners = practitioners;
	}
	
	
	
}
