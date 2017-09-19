package net.endpoint.institute.model.gallery;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import net.endpoint.institute.model.InsPatientProfile;

@Entity
@DiscriminatorValue("G")
public class InsPatientGallery extends InsPatientProfile {


	private static final long serialVersionUID = 1L;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="gallery" ,targetEntity=InsGalleryDocument.class)
	private List<InsGalleryDocument> documents;
	
	
}
