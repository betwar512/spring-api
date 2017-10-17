package net.endpoint.institute.model.gallery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import net.endpoint.institute.model.InsDocument;

@Entity
@DiscriminatorValue("G")
public class InsGalleryDocument extends InsDocument {

	private static final long serialVersionUID = 4679936389053810233L;

	@ManyToOne
	@JoinColumn(name="ins_patient_profile_id")
	private InsPatientGallery gallery;
	@ManyToOne
	@JoinColumn(name="ins_gallery_category_id")
	private InsGalleryCategory category;
	
	
	public InsPatientGallery getGallery() {
		return gallery;
	}
	public InsGalleryCategory getCategory() {
		return category;
	}
	public void setGallery(InsPatientGallery gallery) {
		this.gallery = gallery;
	}
	public void setCategory(InsGalleryCategory category) {
		this.category = category;
	}

	
	
	
}
