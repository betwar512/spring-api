package net.endpoint.institute.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ins_patient_profile")
public class InsPatientProfile {

	
	@Id
	@GeneratedValue
	private long id;
	
	
}
