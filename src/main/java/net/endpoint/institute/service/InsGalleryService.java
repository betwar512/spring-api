package net.endpoint.institute.service;

import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;

public interface InsGalleryService {

	public void findPending(InsPatient patient,InsPractitioner practitioner);
	public void findPendingDocuments(InsPatient patient);
	public void getPatientDocumentMap(InsPatient patient , InsPractitioner practitioner);
}
