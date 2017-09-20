package net.endpoint.institute.service;

import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.gallery.InsPatientGallery;

public interface InsGalleryService {
	/**
	 * Find last active patione doctor gallery if emty createa new one </p>
	 * @param patient
	 * @param practitioner
	 * @return ptientGallery 
	 */
	public InsPatientGallery findOrCreateGallery(InsPatient patient, InsPractitioner practitioner);
	public void findPending(InsPatient patient,InsPractitioner practitioner);
	public void findPendingDocuments(InsPatient patient);
	public void getPatientDocumentMap(InsPatient patient , InsPractitioner practitioner);
}
