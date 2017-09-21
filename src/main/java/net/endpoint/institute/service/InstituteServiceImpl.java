package net.endpoint.institute.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.User;
import net.endpoint.institute.dao.InstituteDao;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;

@Service
@Transactional
public class InstituteServiceImpl implements InstituteService {
	
	@Autowired
	private InstituteDao instituteDao;
	


	/**
	 * <p> Create Patient </p>
	 * @param profileDto
	 * @param practitioner
	 */
	@Override
	public InsPatient createPatient(ProfileDto profileDto){
		//Create person 
			Person person = new Person();
			person.setCreatedAt(new Date());
			person.setDateOfBirth(profileDto.getDob());
			person.setFirstName(profileDto.getFirstname());
			person.setLastName(profileDto.getLastname());
			this.instituteDao.save(person);
		//Save patient 	
			InsPatient patient = new InsPatient();
			patient.setPerson(person);
			this.instituteDao.save(patient);
			return patient;
	    }
	
	
	@Override
	public InsPractitioner loadPractition(long id) {
		return this.instituteDao.loadPractitioner(id);
	}

	@Override
	public InsPractitioner findByPerson(Person person) throws NotFoundException  {
			InsPractitioner result = this.instituteDao.findByPerson(person);
			if(result == null) {
				throw new NotFoundException("Person is not a Practitioner .");
			}
			
			
			
		return result;
	}

	@Override
	public InsPractitioner findByEmail(String email) {
		return this.instituteDao.findByEmail(email);
	}

	@Override
	public InsPractitioner createPractitioner(User user) {		
	InsPractitioner practitioner = 	this.instituteDao.findByEmail(user.getEmail());
	 if(practitioner == null){
		practitioner = new InsPractitioner();
		practitioner.setActive(true);
		practitioner.setUser(user);
		practitioner.setTimestamp(new Date());
		this.instituteDao.save(practitioner);
	   }
	return practitioner;
	}
	
	



	public void setInstituteDao(InstituteDao instituteDao) {
		this.instituteDao = instituteDao;
	}
	
	
}
