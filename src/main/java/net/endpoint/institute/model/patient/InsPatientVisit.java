package net.endpoint.institute.model.patient;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.endpoint.utils.enums.EnumTypes.SERVER_STATUS;

@Entity
@Table(name="ins_patient_profile_visit")
public class InsPatientVisit {
	
	@Id
	@Column(name="ins_patient_visit_id")
	@GeneratedValue	
	private long id;
	@ManyToOne
	@JoinColumn(name="ins_patient_profile_id",nullable=false)
	private InsPatientProfile patientProfile;
	@Column(nullable=false)
	private String note;
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private SERVER_STATUS status;
	@Column
	private Date visitTime;
	@Column
	private Date createdAt;
	
	
	
	public InsPatientVisit() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public InsPatientProfile getPatientProfile() {
		return patientProfile;
	}
	public void setPatientProfile(InsPatientProfile patientProfile) {
		this.patientProfile = patientProfile;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	public SERVER_STATUS getStatus() {
		return status;
	}

	public void setStatus(SERVER_STATUS status) {
		this.status = status;
	}

	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "InsPatientVisit [id=" + id + ", patientProfile=" + patientProfile + ", note=" + note + ", status="
				+ status + ", visitTime=" + visitTime + ", createdAt=" + createdAt + "]";
	}

	
	
}
