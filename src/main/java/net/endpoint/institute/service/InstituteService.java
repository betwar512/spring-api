package net.endpoint.institute.service;


import java.util.Map;
import java.util.Set;
import javassist.NotFoundException;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.User;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;

public interface InstituteService {

	public InsPractitioner findByEmail(String email);
	/**
	 * Create new patient
	 * @param profileDto
	 * @return
	 */
	public InsPatient createPatient(ProfileDto profileDto);
	/**
	 * <p>Create a new Practitioner </p>
	 * @param profileDto
	 * @return
	 */
	public InsPractitioner createPractitioner(User user);
	public Set<InsDocument> loadHistoryByPart(InsPractitioner practitioner,InsPatient  patient, InsPartType type);
	public InsPractitioner loadPractition(long id);
	public InsPractitioner findByPerson(Person person)  throws NotFoundException;
}
