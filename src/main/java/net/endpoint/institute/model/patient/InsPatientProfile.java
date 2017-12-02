package net.endpoint.institute.model.patient;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import net.endpoint.institute.model.InsPractitioner;

@Entity
@Table(name="ins_patient_profile")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="itype")
@DiscriminatorOptions(force=true)
public class InsPatientProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ins_patient_profile_id")
	@GeneratedValue
	private long id;
	@ManyToOne
	@JoinColumn(name="ins_patient_id",nullable=false)
	private InsPatient patient;
	@ManyToOne
	@JoinColumn(name="ins_practitioner_id",nullable=false)
	private InsPractitioner practitioner;
	@Column
	private Date createdAt;
	@Column
	private Date updatedAt;
	@Column(name="i_active",nullable=false)
	private boolean active;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getId() {
		return id;
	}
	public InsPatient getPatient() {
		return patient;
	}
	public InsPractitioner getPractitioner() {
		return practitioner;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPatient(InsPatient patient) {
		this.patient = patient;
	}
	public void setPractitioner(InsPractitioner practitioner) {
		this.practitioner = practitioner;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
