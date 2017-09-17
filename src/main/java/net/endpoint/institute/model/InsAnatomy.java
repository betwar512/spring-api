package net.endpoint.institute.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ins_anatomy")
public class InsAnatomy {
	@Id
	@GeneratedValue
	@Column(name="ins_anatomy_id")
	private long id;
	@ManyToOne
	@JoinColumn(name="ins_patient_id")
	private InsPatient patient;
	@OneToMany(fetch=FetchType.EAGER , mappedBy="anatomy")
	private Set<InsBodyPart> bodyParts = new HashSet<>();
	@Column(name="created_at")
	private Date timestamp;
	
	
	
	
	
	public Set<InsBodyPart> getBodyParts() {
		return bodyParts;
	}
	public void setBodyParts(Set<InsBodyPart> bodyParts) {
		this.bodyParts = bodyParts;
	}
	public long getId() {
		return id;
	}
	public InsPatient getPatient() {
		return patient;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPatient(InsPatient patient) {
		this.patient = patient;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
