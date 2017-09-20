package net.endpoint.institute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.institute.model.InsPractitioner;

@RestController
@RequestMapping(path="/api/ins")
public class InstitudeController extends InsMainController {

	
	
	
	@RequestMapping(path="/createprac")
	public void createPractition(ProfileDto profileDto){
		    profileDto.getEmail();
		
	InsPractitioner practitioner = this.insService
									.findByEmail(profileDto.getEmail());
		
		
		
	}
	
	
	public void createPatient(ProfileDto profileDto){
		
		
		
	}
	
	
}
