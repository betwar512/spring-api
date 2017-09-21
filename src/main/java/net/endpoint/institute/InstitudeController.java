package net.endpoint.institute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.institute.model.InsPractitioner;

@RestController
@RequestMapping(path="/api/ins")
public class InstitudeController extends InsMainController {

	
	
	
	@RequestMapping(path="/createprac")
	public InsPractitioner createPractition(ProfileDto profileDto){
		   String email =  profileDto.getEmail();
		   if(this.isAdmin()){
	InsPractitioner practitioner = this.insService
									.findByEmail(profileDto.getEmail());
		return practitioner;
		   }
		
		   return null;
	}
	
	
	public void createPatient(ProfileDto profileDto){
		
		
		
	}
	
	
}
