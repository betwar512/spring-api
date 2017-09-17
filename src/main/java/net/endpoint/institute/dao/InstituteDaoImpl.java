package net.endpoint.institute.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.main.dao.BaseDao;

@Transactional
public class InstituteDaoImpl extends BaseDao implements InstituteDao {

	
	
	@Override
	public List<InsPatient> getAll(InsPractitioner prac) {

		return null;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public InsPractitioner loadPractitioner(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InsPatient loadPatient(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
