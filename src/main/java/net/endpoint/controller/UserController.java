package net.endpoint.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.SecurityRole;
import net.endpoint.model.User;
import net.endpoint.service.UserService;
import net.endpoint.service.email.EmailService;


@RestController
@RequestMapping("/api/user")
public class UserController extends MainController {

	
		@Autowired
		UserService userService;
		@Autowired
		EmailService emailService;
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		@RequestMapping(method = RequestMethod.GET)
		public ProfileDto get(){	
    	 ProfileDto dto = loadProfile();
    	 logger.debug(dto.toString());
    	 return dto;
		}
		
		@RequestMapping(value="/all",method = RequestMethod.GET)
		@Transactional
		public List<ProfileDto> getAll(){
		  boolean isAuth = true;//this.loadUser().getRolse().stream().filter(t-> t.getLevel() > 4).count() > 0;
		  List<ProfileDto> profiles = new ArrayList<>();
		  
		  this.userService.getAll().forEach(t->{
			 ProfileDto pr =   new ProfileDto();
			 pr.pars(t.getPerson());
			  profiles.add(pr);
			  
			  
		  });
			//return isAuth? this.userService.getAll() : null;
		  return profiles;
		}
		
		@RequestMapping(value="/create",method = RequestMethod.POST)
		public AccountRequestDto create(@RequestBody AccountRequestDto accountRequestDto){	
			   User user = this.loadUser();
			   
			   Set<SecurityRole> roles = user.getRolse();
			   Stream<SecurityRole> result = roles.stream().filter(t-> t.getLevel() > 4);
			   
		 if(result.count() > 0){  
			   try {
				this.userService.createAccount(accountRequestDto);
			} catch (Exception e) {
				logger.error(e);
			}
			}
			return new AccountRequestDto();
		}
		
		
		@RequestMapping(value="/update",method = RequestMethod.POST)
		public ProfileDto update(@RequestBody ProfileDto profile){
			String name = this.getUserName();
			profile.setEmail(name);
			this.userService.updateProfile(profile);
			return profile;
		}
		
		@RequestMapping(value="/update/address",method = RequestMethod.POST)
		public AddressDto createOrUpdateAddress(@RequestBody AddressDto address){
			 userService.createOrUpdateAddress(address);
			 return address;
		}
		
	
		public void delete(){}
	
	
}
