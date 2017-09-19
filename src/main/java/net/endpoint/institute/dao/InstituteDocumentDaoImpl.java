package net.endpoint.institute.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsAnatomy;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;
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
	@SuppressWarnings("unchecked")
	public List<InsDocument> getPartDocuments(InsPractitioner practitioner, InsPatient patient, InsPartType type) {
	String hql ="select doc from InsDocument doc , InsPatientAnatomy pa where doc.part.type =:type "
            + " and pa.patient=:patient and pa.practitioner=:practitioner"
            + "and doc.part.anatomy = pa.anatomy order by doc.timestamp desc";
       List<InsDocument> list =	this.getSession()
    		   .createQuery(hql)
    		   .setEntity("practitioner", practitioner)
           	  .setEntity("patient", patient)
	          .setEntity("type", type)
	          .list();
		return list!=null ? list : new ArrayList<>();
	}

	public InsDocument findLastDocumentForBodytype(InsAnatomy anatomy,InsPartType type) {
	final	String hql = "from InsDocument doc where doc.part.type =:type "
				             + "and doc.part.anatomy =:anatomy order by doc.timestamp desc";
	return    (InsDocument) this.getSession().createQuery(hql).setEntity("type", type)
                                                 .setEntity("anatomy", anatomy).setMaxResults(1);
	}
	
	
}
