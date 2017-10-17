package net.endpoint.institute.service;

import javassist.NotFoundException;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.User;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;

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
/**
 * <p>Load proxy object by Id </p>
 * @param id
 * @return
 */
	public InsPractitioner loadPractition(long id);
	
	/**
	 * Load person from Account package </p>
	 * @param person
	 * @return 
	 * @throws NotFoundException
	 */
	public InsPractitioner findByPerson(Person person)  throws NotFoundException;
}
