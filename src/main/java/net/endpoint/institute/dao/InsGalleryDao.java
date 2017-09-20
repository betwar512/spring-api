package net.endpoint.institute.dao;

import java.util.List;

import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.gallery.InsGalleryDocument;
import net.endpoint.institute.model.gallery.InsPatientGallery;

public interface InsGalleryDao {

	public List<InsGalleryDocument>  getAllNewDocuments(InsPatient patient, String practitionEmail);
	public List<InsGalleryDocument>    getAllDocuments();
	public InsPatientGallery findGallery(InsPatient patient,InsPractitioner practitioner);
	public void save(Object object);
}
