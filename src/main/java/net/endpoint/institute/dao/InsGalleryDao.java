package net.endpoint.institute.dao;

import java.util.List;

import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.gallery.InsGalleryDocument;

public interface InsGalleryDao {

	public List<InsGalleryDocument>  getAllNewDocuments(InsPatient patient, String practitionEmail);
	public List<InsGalleryDocument>    getAllDocuments();
	
	
}
