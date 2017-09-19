package net.endpoint.institute.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
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
import net.endpoint.institute.model.anatomy.InsBodyPart;
import net.endpoint.institute.model.anatomy.InsPartType;
import net.endpoint.institute.model.anatomy.InsPatientAnatomy;
import net.endpoint.institute.model.anatomy.InsBodyPart.BODY_PART_TYPE;

@Service
public class InstituteServiceImpl implements InstituteService {
	
	@Autowired
	private InstituteDao instituteDao;
	

	@Override
	public InsDocument loadLastDocumentByPart(InsPatientAnatomy anatomyPation , InsPartType type) {
				
	   Optional<InsBodyPart> bodyPart =  anatomyPation.getAnatomy().getBodyParts().stream().filter(t->t.getType().equals(type)).findFirst();
	   if(bodyPart.isPresent()) {
		Optional<InsDocument> doc = bodyPart.get().getDocuments().stream().sorted(
					  (o1,o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt()))
					  .findFirst();
			if(doc.isPresent()) {
				return doc.get();
			}
	   }
		return null;
	}

	@Override
	public Map<InsPartType,InsDocument> loadLastDocuemtnForAllTypes(InsPractitioner practitioner,InsPatient patient) {
    	InsPatientAnatomy anatomyPation = this.loadForPractitioner(practitioner, patient);
    	Map<InsPartType,InsDocument> map = new HashMap<>();
		anatomyPation.getAnatomy().getBodyParts().forEach(t->{	
		  Optional<InsDocument> doc = t.getDocuments().stream().sorted(
				  (o1,o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt()))
				  .findFirst();
		if(doc.isPresent())
		 	 map.put(t.getType(), doc.get());
		});
	 return map;
	}

	@Override
	public InsPractitioner loadPractition(long id) {
		return this.instituteDao.loadPractitioner(id);
	}



	public void setInstituteDao(InstituteDao instituteDao) {
		this.instituteDao = instituteDao;
	}

	@Override
	public InsPatientAnatomy loadForPation(Person person, InsPatient patients) throws NotFoundException {
		InsPractitioner practitioner =    this.findByPerson(person); 
		return   this.instituteDao.loadAnatomy(practitioner, patients);

	}

	@Override
	public InsPractitioner findByPerson(Person person) throws NotFoundException  {
			InsPractitioner result = this.instituteDao.findByPerson(person);
			if(result == null) {
				throw new NotFoundException("Person is not a Practitioner .");
			}
			
			
			
		return result;
	}

	@Override
	public Set<InsDocument> loadHistoryByPart(InsPractitioner practitioner, InsPatient patient, InsPartType type) {
		 InsPatientAnatomy                insAnt  =  this.instituteDao.loadAnatomy(practitioner, patient);
		 if(insAnt == null) {
			 throw new NoSuchElementException("Anatomy for this ptation not exist.");
		 }
		 List<InsBodyPart> streamPromis = insAnt.getAnatomy().getBodyParts().stream().filter(t->t.getType().equals(type)).collect(Collectors.toList());
		   InsBodyPart item = !streamPromis.isEmpty() ? streamPromis.get(0) : null;
		   if(item == null) {
				 throw new NoSuchElementException("Body part dont exist for pation " + type);
		   }
		return item.getDocuments() ;
	}

	@Override
	public InsPatientAnatomy loadForPractitioner(InsPractitioner practitioner, InsPatient patients)  {
		    return   this.instituteDao.loadAnatomy(practitioner, patients);
	}

	
	@Override
	public void createAnatomy(InsPractitioner practitioner, InsPatient patient) {
			InsAnatomy anatomy =  	this.createNewAnatomy();
			InsPatientAnatomy ips = new InsPatientAnatomy();
			ips.setAnatomy(anatomy);
			ips.setPatient(patient);
			ips.setPractitioner(practitioner);
			this.instituteDao.save(ips);
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
