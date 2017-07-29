package net.endpoint.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import net.endpoint.config.security.IAuthenticationFacade;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.service.GlobalSettingsService;

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

	protected ProfileDto loadProfile(){	
		  return  service.loadProfile(getUserName());
	}
	
	protected User loadUser(){
		return service.loadUser(getUserName());
	}
	
	protected String getUserName(){
		return	authenticationFacade.getAuthentication().getName();
	}
	
	
}
