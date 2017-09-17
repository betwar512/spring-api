package net.endpoint.institute.dao;

import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;

public interface InstituteDao {

	public void save(Object o);
	public InsPractitioner loadPractitioner(long id);
	public InsPatient loadPatient(long id);
}
