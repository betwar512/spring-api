package net.endpoint.institute.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import net.endpoint.account.model.Person;
import net.endpoint.institute.dao.InstituteDao;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.anatomy.InsAnatomy;
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
			InsAnatomy anatomy =  	this.createNewAnatomy();
//			InsPatientAnatomy ips = new InsPatientAnatomy();
//			ips.setAnatomy(anatomy);
//			ips.setPatient(patient);
//			ips.setPractitioner(practitioner);
//			this.instituteDao.save(ips);
	}
	


	@Override
	public InsPatientAnatomy loadForPation(Person person, InsPatient patients) throws NotFoundException {
		InsPractitioner practitioner =    this.instituteDao.findByPerson(person); 
		return   this.instituteDao.loadAnatomy(practitioner, patients);

	}
	
	
	@Override
	public Map<InsPartType,InsDocument> loadLastDocuemtnForAllTypes(InsPractitioner practitioner,InsPatient patient) {
    	InsPatientAnatomy anatomyPation = this.loadForPractitioner(practitioner, patient);
    	Map<InsPartType,InsDocument> map = new HashMap<>();
//		anatomyPation.getAnatomy().getBodyParts().forEach(t->{	
//		  Optional<InsDocument> doc = t.getDocuments().stream().sorted(
//				  (o1,o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt()))
//				  .findFirst();
//		if(doc.isPresent())
//		 	 map.put(t.getType(), doc.get());
//		});
	 return map;
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

}
