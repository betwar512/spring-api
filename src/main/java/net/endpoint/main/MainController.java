package net.endpoint.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.User;
import net.endpoint.config.security.IAuthenticationFacade;
import net.endpoint.main.service.GlobalSettingsService;

/**
 * <p>Main controller abstract class to
 *  extend Controller access data such and user oath </p>
 * @author A.H.Safaie
 *
 */
public abstract class MainController {

	protected static final Logger logger = Logger.getLogger(MainController.class);
	@Autowired
	protected GlobalSettingsService globleService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;

	public void setAuthenticationFacade(IAuthenticationFacade authenticationFacade) {
		this.authenticationFacade = authenticationFacade;
	}

	
	public IAuthenticationFacade getAuthenticationFacade() {
		return authenticationFacade;
	}
	/**
	 * <p>Load current profile for user </p>
	 * @return ProfileDto
	 */
	protected ProfileDto loadProfile(){	
		  return  globleService.loadProfile(getUserName());
	}
	/**
	 * <p>Load a current user </p>
	 * @return User
	 */
	protected User loadUser(){
		return globleService.loadUser(getUserName());
	}
	/**
	 *<p> Get current user name</p> 
	 * @return user-name String
	 */
	protected String getUserName(){
		return	authenticationFacade.getAuthentication().getName();
	}

	protected boolean isAdmin() {
		User user =   this.loadUser();
		return user.getRolse().stream().anyMatch(t->t.getLevel() > 3);
	}
	
}
