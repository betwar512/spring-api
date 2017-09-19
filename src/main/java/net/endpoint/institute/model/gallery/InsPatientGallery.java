package net.endpoint.institute.model.gallery;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.endpoint.institute.model.InsPatientProfile;

@Entity
@DiscriminatorValue("G")
public class InsPatientGallery extends InsPatientProfile {


	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ins_document_id")
	private List<InsGalleryDocument> documents;
	
	
}
