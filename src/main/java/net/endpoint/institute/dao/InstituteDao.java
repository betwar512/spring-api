package net.endpoint.institute.dao;

import net.endpoint.account.model.Person;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;

public interface InstituteDao {

	public void save(Object o);
	public InsPractitioner loadPractitioner(long id);
	public InsPractitioner findByPerson(Person person);
	public InsPatient loadPatient(long id);
	public InsPatientAnatomy loadAnatomy(InsPractitioner practitioner , InsPatient patient);
}
