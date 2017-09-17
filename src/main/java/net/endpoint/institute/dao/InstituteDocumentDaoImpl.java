package net.endpoint.institute.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPartType;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPatientAnatomy;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.main.dao.BaseDao;

public class InstituteDocumentDaoImpl extends BaseDao implements InstituteDocumentDao {

	@Override
	public InsDocument getLastDocument(InsPractitioner practitioner, InsPatient patien, InsPartType type) {
			this.getSession().createCriteria(InsPatientAnatomy.class)
			.createAlias("anatomy", "anatomy")
			.add(Restrictions.eq("patient", patien))
			.add(Restrictions.eq("practitioner", practitioner));
			this.getSession().createQuery("from InsDocument ");
			
			
			
		return null;
	}

	@Override
	public List<InsDocument> getPartDocuments(InsPractitioner practitioner, InsPatient patien, InsPartType type) {
	
		return null;
	}

}
