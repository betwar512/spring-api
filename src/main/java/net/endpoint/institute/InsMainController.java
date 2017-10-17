package net.endpoint.institute;

import org.springframework.beans.factory.annotation.Autowired;
import net.endpoint.institute.model.InsPractitioner;
import net.endpoint.institute.service.InstituteService;
import net.endpoint.main.MainController;



/**
 * 
 * @author A.H.Safaie
 *
 */
public abstract class InsMainController extends MainController{

	@Autowired
   protected InstituteService insService;
	
	protected InsPractitioner loadCurrentPractitioner() {	
	  InsPractitioner Profile = this.insService.findByEmail(this.getUserName());
	  return Profile;
	}

	public void setInsService(InstituteService insService) {
		this.insService = insService;
	}
	
}
