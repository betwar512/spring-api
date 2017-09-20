package net.endpoint.institute.dao;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.endpoint.account.dao.ProfileDao;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Person;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;
import net.endpoint.institute.model.gallery.InsPatientGallery;
import net.endpoint.main.dao.BaseDao;

@Transactional
@Repository
public class InstituteDaoImpl extends BaseDao implements InstituteDao {

	@Autowired
	private ProfileDao profileDao;

	@Override
	public InsPractitioner loadPractitioner(long id) {
		return this.getSession().load(InsPractitioner.class, id);
	}

	@Override
	public InsPatient loadPatient(long id) {
		return this.getSession().load(InsPatient.class, id);
	}

	@Override
	public InsPatientAnatomy loadAnatomy(InsPractitioner practitioner, InsPatient patient) {
		@SuppressWarnings("unchecked")
		List<InsPatientAnatomy> list=	  this.getSession().createCriteria(InsPatientAnatomy.class)
			.add(Restrictions.eq("patient",patient))
			.add(Restrictions.eq("practitioner", practitioner)).list();
			
		return list!=null && !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * <p> Create Patient </p>
	 * @param profileDto
	 * @param practitioner
	 */
	public void createPatient(ProfileDto profileDto,InsPractitioner practitioner){
		//Create person 
			Person person = new Person();
			person.setCreatedAt(new Date());
			person.setDateOfBirth(profileDto.getDob());
			person.setFirstName(profileDto.getFirstname());
			person.setLastName(profileDto.getLastname());
			this.save(person);
		//Save patient 	
			InsPatient patient = new InsPatient();
			patient.setPerson(person);
			this.save(patient);
		//Create patientProfile
			InsPatientGallery gallery = new InsPatientGallery();
			gallery.setPatient(patient);
			gallery.setPractitioner(practitioner);
			gallery.setUpdatedAt(new Date());
			gallery.setCreatedAt(new Date());
			this.save(gallery);
			
	}
	
	
	
	@Override
	public InsPractitioner findByPerson(Person person) {
			@SuppressWarnings("unchecked")
			List<InsPractitioner> list = this.getSession()
			.createCriteria(InsPractitioner.class)	
			.add(Restrictions.eq("person", person))
            .list();										
		return list != null && !list.isEmpty() ? list.get(0)  : null;
	}

	@Override
	public InsPractitioner findByEmail(String email) {
			return	   (InsPractitioner) this.getSession()
				.createQuery("from InsPractitioner ins where ins.person.user.email =:email")
				.setEntity("email", email)
				.uniqueResult();
	}
	

}
