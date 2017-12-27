package net.endpoint.institute;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.User;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.model.patient.InsPatient;

@RestController
@RequestMapping(path="/api/ins")
public class InstitudeController extends InsMainController {

	
	@RequestMapping(path="/create/prac")
	public InsPractitioner createPractition(@RequestBody ProfileDto profileDto){
	InsPractitioner practitioner = this.insService.findByEmail(profileDto.getEmail());
		if(practitioner == null && this.isAdmin()) {
		       User user = this.globleService.loadUser(profileDto.getEmail());
			practitioner = this.insService.createPractitioner(user);
		}		
	return practitioner;

	}
	
	@RequestMapping(path="/create/patient")
	public InsPatient createPatient(@RequestBody ProfileDto profileDto){
	return 	this.insService.createPatient(profileDto);
	}
	
	
}
