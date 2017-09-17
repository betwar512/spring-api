package net.endpoint.institute.dao;

import java.util.List;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;

public interface InstituteDao {

	public List<InsPatient> getAll(InsPractitioner prac);
	public void save(Object o);
	public InsPractitioner loadPractitioner(long id);
	public InsPatient loadPatient(long id);
}
