package net.endpoint.institute.service;


import java.util.Map;
import java.util.Set;
import javassist.NotFoundException;
import net.endpoint.account.model.Person;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;

public interface InstituteService {

	public Set<InsDocument> loadHistoryByPart(InsPractitioner practitioner,InsPatient  patient, InsPartType type);
	public InsDocument loadLastDocumentByPart(InsPatientAnatomy anatomyPation , InsPartType type);
	public Map<InsPartType,InsDocument> loadLastDocuemtnForAllTypes(InsPractitioner practitioner,InsPatient patient);
	public InsPractitioner loadPractition(long id);
	public InsPractitioner findByPerson(Person person)  throws NotFoundException;
	public InsPatientAnatomy loadForPation(Person person,InsPatient patients)  throws NotFoundException ;
	public InsPatientAnatomy loadForPractitioner(InsPractitioner practitioner, InsPatient patient)   ;
	public void createAnatomy(InsPractitioner practitioner, InsPatient patient) ;
}
