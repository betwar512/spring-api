package net.endpoint.institute.service;

import java.util.List;
import java.util.Map;
import javassist.NotFoundException;
import net.endpoint.account.model.Person;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsAnatomyDocument;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;

public interface InsAnatomyService {

	public InsPatientAnatomy loadForPation(Person person,InsPatient patients)  throws NotFoundException ;
	public InsPatientAnatomy loadForPractitioner(InsPractitioner practitioner, InsPatient patient)   ;
	public void createAnatomy(InsPractitioner practitioner, InsPatient patient) ;
	public Map<InsPartType,InsDocument> loadLastDocuemtnForAllTypes(InsPractitioner practitioner,InsPatient patient);
	public List<InsAnatomyDocument> loadHistoryByPart(InsPractitioner practitioner,InsPatient  patient, InsPartType type);
}
