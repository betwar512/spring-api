package net.endpoint.institute.dao;

import java.util.List;

import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPartType;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;

public interface InstituteDocumentDao {

	public InsDocument getLastDocument(InsPractitioner practitioner,InsPatient patien,InsPartType type);
	public List<InsDocument> getPartDocuments(InsPractitioner practitioner,InsPatient patien,InsPartType type);
	
}
