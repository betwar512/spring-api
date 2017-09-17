package net.endpoint.institute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.endpoint.account.dto.ProfileDto;
import net.endpoint.institute.dao.InstituteDao;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPartType;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPatientAnatomy;
import net.endpoint.institute.model.InsPractitioner;

@Service
public class InstituteServiceImpl implements InstituteService {
	
	@Autowired
	private InstituteDao instituteDao;
	
	@Override
	public List<InsDocument> loadHistoryByPart(InsPatient patient, InsPartType type) {
				if(patient!=null){
					
				}
		return null;
	}

	@Override
	public InsDocument loadLastDocumentByPart(InsPatient patient, InsPartType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsDocument> loadLastDocuemtnForAllTypes(InsPatient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InsPractitioner loadPractition(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InsPractitioner loadByProfile(ProfileDto profileDto) {

		return null;
	}

	public void setInstituteDao(InstituteDao instituteDao) {
		this.instituteDao = instituteDao;
	}

	@Override
	public InsPatientAnatomy loadForPation(ProfileDto profileDto, InsPatient patients) {
		InsPractitioner practitioner =    this.loadByProfile(profileDto); 
		return   this.instituteDao.loadAnatomy(practitioner, patients);

	}

	
}
