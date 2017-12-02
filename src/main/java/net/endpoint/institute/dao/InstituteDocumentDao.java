package net.endpoint.institute.dao;

import java.util.List;

import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.patient.InsPatient;

public interface InstituteDocumentDao {

	public InsDocument getLastDocument(InsPractitioner practitioner,InsPatient patien,InsPartType type);
	public List<InsDocument> getPartDocuments(InsPractitioner practitioner,InsPatient patien,InsPartType type);
	
}
