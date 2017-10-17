package net.endpoint.institute.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;
import net.endpoint.account.model.Person;
import net.endpoint.institute.dao.InstituteDao;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsAnatomy;
import net.endpoint.institute.model.anatomy.InsAnatomyDocument;
import net.endpoint.institute.model.anatomy.InsBodyPart;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;
import net.endpoint.institute.model.anatomy.InsBodyPart.BODY_PART_TYPE;

@Service
public class InsAnatomyServiceImpl implements InsAnatomyService {

	
	@Autowired
	private InstituteDao instituteDao;
	
	
	@Override
	public InsPatientAnatomy loadForPractitioner(InsPractitioner practitioner, InsPatient patients)  {
		    return   this.instituteDao.loadAnatomy(practitioner, patients);
	}

	
	@Override
	public void createAnatomy(InsPractitioner practitioner, InsPatient patient) {
		
	}
	


	@Override
	public InsPatientAnatomy loadForPation(Person person, InsPatient patients) throws NotFoundException {
		InsPractitioner practitioner =    this.instituteDao.findByPerson(person); 
		return   this.instituteDao.loadAnatomy(practitioner, patients);

	}
	
	
	@Override
	public Map<InsPartType,InsDocument> loadLastDocuemtnForAllTypes(InsPractitioner practitioner,InsPatient patient) {

	 return null;
	}
	
	private InsAnatomy createNewAnatomy() {
		InsAnatomy anatomy = new InsAnatomy();
		this.instituteDao.save(anatomy);
	for	(BODY_PART_TYPE   val : BODY_PART_TYPE.values()) {
		InsBodyPart part = new InsBodyPart();
		part.setAnatomy(anatomy);
		part.setTimestamp(new Date());
		//TODO types 
		//part.setType("");
		this.instituteDao.save(part);
     	}
		return anatomy;
	}


	@Override
	public List<InsAnatomyDocument> loadHistoryByPart(InsPractitioner practitioner, InsPatient patient, InsPartType type) {
		 InsPatientAnatomy                insAnt  =  this.instituteDao.loadAnatomy(practitioner, patient);
		 if(insAnt == null) {
			 throw new NoSuchElementException("Anatomy for this ptation not exist.");
		 }
		 List<InsAnatomyDocument> list = insAnt.getDocuments().stream()
				 .filter(t->t.getPart().getType().equals(type)).collect(Collectors.toList());
		   if(list == null) {
				 throw new NoSuchElementException("Body part dont exist for pation " + type);
		   }
		return list != null ? list : new ArrayList<>();
	}



}
