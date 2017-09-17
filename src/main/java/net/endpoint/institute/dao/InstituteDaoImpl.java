package net.endpoint.institute.dao;

import javax.transaction.Transactional;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.main.dao.BaseDao;

@Transactional
public class InstituteDaoImpl extends BaseDao implements InstituteDao {


	@Override
	public void save(Object o) {
		this.getSession().saveOrUpdate(o);
	}

	@Override
	public InsPractitioner loadPractitioner(long id) {
		return this.getSession().load(InsPractitioner.class, id);
	}

	@Override
	public InsPatient loadPatient(long id) {
		return this.getSession().load(InsPatient.class, id);
	}
	

}
