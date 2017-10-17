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

	/**
	 * Load patient Anatomy 
	 * @param person
	 * @param patients
	 * @return InsPatientAnatomy 
	 * @throws NotFoundException
	 */
	public InsPatientAnatomy loadForPation(Person person,InsPatient patients)  throws NotFoundException ;
	/**
	 * Load
	 * @param practitioner
	 * @param patient
	 * @return
	 */
	public InsPatientAnatomy loadForPractitioner(InsPractitioner practitioner, InsPatient patient)   ;
	/**
	 * Each doctor and patient has a separate anatomy record </p>
	 * <p>create a anatomy for this patient and doctor </p>
	 * @param practitioner
	 * @param patient
	 */
	public void createAnatomy(InsPractitioner practitioner, InsPatient patient) ;
	/**
	 * 
	 * @param practitioner
	 * @param patient
	 * @return
	 */
	public Map<InsPartType,InsDocument> loadLastDocuemtnForAllTypes(InsPractitioner practitioner,InsPatient patient);
	/**
	 * Load history 
	 * @param practitioner
	 * @param patient
	 * @param type
	 * @return
	 */
	public List<InsAnatomyDocument> loadHistoryByPart(InsPractitioner practitioner,InsPatient  patient, InsPartType type);
}
