package net.endpoint.institute.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.endpoint.institute.dao.InsGalleryDao;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.gallery.InsPatientGallery;

@Service
public class InsGalleryServiceImpl implements InsGalleryService{


	@Autowired
	private InsGalleryDao galleryDao;
	
	@Override
	public void findPending(InsPatient patient, InsPractitioner practitioner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findPendingDocuments(InsPatient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPatientDocumentMap(InsPatient patient, InsPractitioner practitioner) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public InsPatientGallery findOrCreateGallery(InsPatient patient, InsPractitioner practitioner) {
		InsPatientGallery gallery = null;
		if(  patient != null && practitioner != null){
		    gallery = this.galleryDao.findGallery(patient, practitioner);
		if(gallery == null){
			gallery = new InsPatientGallery();
			gallery.setActive(true);
			gallery.setCreatedAt(new Date());
			gallery.setPatient(patient);
			gallery.setPractitioner(practitioner);
			this.galleryDao.save(gallery);
		  }
		}	 
		return gallery;
	}

}
