package net.endpoint.institute.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.gallery.InsGalleryDocument;
import net.endpoint.institute.model.gallery.InsPatientGallery;
import net.endpoint.institute.model.patient.InsPatient;
import net.endpoint.main.dao.BaseDao;

@Repository
@Transactional
public class InsGalleryDaoImpl extends BaseDao implements InsGalleryDao {

	@Override
	public List<InsGalleryDocument> 
	getAllNewDocuments(InsPatient patient, String practitionEmail) {

		String hql = "select ins.documents as document from InsPatientGallery ins where ins.patient=:patient "
				     + "and ins.practitioner.person.user.email =:email "
				     + "and document.serverStatus = 'NEW' ";
		
		return null;
	}

	@Override
	public List<InsGalleryDocument> getAllDocuments() {
		
		return null;
	}

	@Override
	public InsPatientGallery findGallery(InsPatient patient, InsPractitioner practitioner) {
			String hql = "from InsPatientGallery ins where "
					+ "ins.patient = :=patient "
					+ "and ins.practitioner =:practitioner "
					+ "and ins.active = true ";
		@SuppressWarnings("unchecked")
		List<InsPatientGallery> galleries = 
		this.getSession().createQuery(hql)
			.setEntity("patient", patient)
			.setEntity("practitioner",practitioner)
			.list();
		return galleries != null && !galleries.isEmpty() ? galleries.get(0) : null;
	}

}
