package net.endpoint.institute.model.anatomy;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import net.endpoint.institute.model.InsPatientProfile;

@Entity
@DiscriminatorValue("A")
public class InsPatientAnatomy extends InsPatientProfile  {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="anatomy" ,targetEntity=InsAnatomyDocument.class)
	private List<InsAnatomyDocument> documents;

	
	public List<InsAnatomyDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<InsAnatomyDocument> documents) {
		this.documents = documents;
	}

	
}
