package net.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import net.endpoint.dto.account.ProfileDto;
import net.endpoint.service.GlobalSettingsService;

public abstract class MainController {

	
	@Autowired
	GlobalSettingsService service;
	
	
	public ProfileDto loadProfile(String email){	
		if(!StringUtils.isEmpty(email)){
		  return  service.loadProfile(email);
		  }
		return null;
	}
	
	
}
