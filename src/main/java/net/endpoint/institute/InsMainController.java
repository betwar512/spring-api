package net.endpoint.institute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.service.InstituteService;
import net.endpoint.main.MainController;



@RestController
public class InsMainController extends MainController{

	@Autowired
private	InstituteService insService;
	
	protected InsPractitioner currentPractitioner() {	
	  InsPractitioner Profile = this.insService.findByEmail(this.getUserName());
		return Profile;
	}
	
	
	
	
}
