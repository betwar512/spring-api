package net.endpoint.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import net.endpoint.config.security.IAuthenticationFacade;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.service.GlobalSettingsService;

/**
 * <p>Main controller abstract class to
 *  extend Controller access data such and user oath </p>
 * @author A.H.Safaie
 *
 */
public abstract class MainController {

	protected static final Logger logger = Logger.getLogger(MainController.class);
	@Autowired
	GlobalSettingsService service;
	
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
		  return  service.loadProfile(getUserName());
	}
	/**
	 * <p>Loaf a current user </p>
	 * @return User
	 */
	protected User loadUser(){
		return service.loadUser(getUserName());
	}
	/**
	 *<p> Get current user name</p> 
	 * @return user-name String
	 */
	protected String getUserName(){
		return	authenticationFacade.getAuthentication().getName();
	}

	
}
