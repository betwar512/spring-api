package net.endpoint.institute.model.anatomy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.patient.InsPatientAnatomy;

@Entity
@DiscriminatorValue("A")
public class InsAnatomyDocument extends InsDocument{

	private static final long serialVersionUID = -2383500042733790107L;
	@ManyToOne
	@JoinColumn(name="ins_body_part_id")
	private InsBodyPart part;
	@ManyToOne
	@JoinColumn(name="ins_patient_profile_id")
	private InsPatientAnatomy anatomy;
	
	public InsBodyPart getPart() {
		return part;
	}
	public void setPart(InsBodyPart part) {
		this.part = part;
	}
	public InsPatientAnatomy getAnatomy() {
		return anatomy;
	}
	public void setAnatomy(InsPatientAnatomy anatomy) {
		this.anatomy = anatomy;
	}
	
}
