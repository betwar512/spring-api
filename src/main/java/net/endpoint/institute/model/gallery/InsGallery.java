package net.endpoint.institute.model.gallery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ins_gallery_document")
public class InsGallery {

	@Id
	@GeneratedValue
	private long id;
	
	
	
}
