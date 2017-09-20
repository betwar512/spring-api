package net.endpoint.institute.dao;

import java.util.List;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.gallery.InsGalleryDocument;
import net.endpoint.main.dao.BaseDao;

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
		// TODO Auto-generated method stub
		return null;
	}

}
