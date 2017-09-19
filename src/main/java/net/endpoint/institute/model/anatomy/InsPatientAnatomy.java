package net.endpoint.institute.model.anatomy;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import net.endpoint.institute.model.InsPatientProfile;

@Entity
@DiscriminatorValue("A")
public class InsPatientAnatomy extends InsPatientProfile  {

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name="ins_document_id") 
	private List<InsAnatomyDocument> documents;
	
	
}
