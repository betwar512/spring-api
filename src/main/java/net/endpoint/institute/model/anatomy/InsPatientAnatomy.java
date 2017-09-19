package net.endpoint.institute.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ins_patient_anatomy")
public class InsPatientAnatomy implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue
	@Column(name="ins_patient_anatomy_id")
	private long id;
	@ManyToOne
	@JoinColumn(name="ins_anatomy",nullable=false)
	private InsAnatomy anatomy;
	@ManyToOne
	@JoinColumn(name="ins_patient_id",nullable=false)
	private InsPatient patient;
	@ManyToOne
	@JoinColumn(name="ins_practitioner_id",nullable=false)
	private InsPractitioner practitioner;
	public long getId() {
		return id;
	}
	public InsAnatomy getAnatomy() {
		return anatomy;
	}
	public InsPatient getPatient() {
		return patient;
	}
	public InsPractitioner getPractitioner() {
		return practitioner;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setAnatomy(InsAnatomy anatomy) {
		this.anatomy = anatomy;
	}
	public void setPatient(InsPatient patient) {
		this.patient = patient;
	}
	public void setPractitioner(InsPractitioner practitioner) {
		this.practitioner = practitioner;
	}
	
	
	
}
