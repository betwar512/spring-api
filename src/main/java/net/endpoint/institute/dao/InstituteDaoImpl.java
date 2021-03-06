package net.endpoint.institute.dao;


import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import net.endpoint.account.model.Person;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;
import net.endpoint.main.dao.BaseDao;

@Transactional
@Repository
public class InstituteDaoImpl extends BaseDao implements InstituteDao {

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

	public void createPractition(String email){
		
		
		
	}

	
}
