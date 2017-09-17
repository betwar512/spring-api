package net.endpoint.institute.service;

import java.util.List;

import net.endpoint.account.dto.ProfileDto;
import net.endpoint.institute.model.InsDocument;
import net.endpoint.institute.model.InsPartType;
import net.endpoint.institute.model.InsPatient;
import net.endpoint.institute.model.InsPatientAnatomy;
import net.endpoint.institute.model.InsPractitioner;

public interface InstituteService {

	
	public List<InsDocument> loadHistoryByPart(InsPatient patient , InsPartType type);
	public InsDocument loadLastDocumentByPart(InsPatient patient, InsPartType type);
	public List<InsDocument> loadLastDocuemtnForAllTypes(InsPatient patient);
	public InsPractitioner loadPractition(long id);
	public InsPractitioner loadByProfile(ProfileDto profileDto);
	public InsPatientAnatomy loadForPation(ProfileDto profileDto,InsPatient patients);
}
