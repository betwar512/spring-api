package net.endpoint.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.account.dto.AccountRequestDto;
import net.endpoint.account.dto.AddressDto;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.SecurityRole;
import net.endpoint.account.model.User;
import net.endpoint.account.service.UserService;
import net.endpoint.emailtemplate.service.EmailService;
import net.endpoint.emailtemplate.service.EmailTemplateServiceImpl;


@RestController
@RequestMapping("/api/user")
public class UserController extends MainController {

	
		@Autowired
		UserService userService;
		@Autowired
		EmailService emailService;
		@Autowired
		EmailTemplateServiceImpl etp;
		
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		@RequestMapping(method = RequestMethod.GET)
		public ProfileDto get(){	
    	 ProfileDto dto = loadProfile();
    	 logger.debug(dto.toString());
    	 try {
  
			this.etp.sendEditableMail("abbas", "betwar512@gmail.com","",new Locale("en"));
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
    	 return dto;
		}
		
		@RequestMapping(value="/all",method = RequestMethod.GET)
		public List<ProfileDto> getAll(){
		  boolean isAuth = true;
		  List<ProfileDto> profiles = this.userService.getAllProfileDtos();
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
