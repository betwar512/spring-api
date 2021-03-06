package net.endpoint.account;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.account.dto.AccountRequestDto;
import net.endpoint.account.dto.AddressDto;
import net.endpoint.account.dto.PhoneDto;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.service.UserService;
import net.endpoint.emailtemplate.service.EmailService;
import net.endpoint.emailtemplate.service.EmailTemplateServiceImpl;
import net.endpoint.main.MainController;


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
    	 return dto;
		}
		
		@RequestMapping(value="/all",method = RequestMethod.GET)
		public List<ProfileDto> getAll(){
		  return  this.isAdmin() ?this.userService.getAllProfileDtos() : new ArrayList<>();
		}
		
		@RequestMapping(value="/create",method = RequestMethod.POST)
		public AccountRequestDto create(@RequestBody AccountRequestDto accountRequestDto){	
		 if(this.isAdmin()){  
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
			 userService.createOrUpdateAddress(address, getUserName());
			 return address;
		}
		
		@RequestMapping(value="/update/phone",method = RequestMethod.POST)
		public PhoneDto createOrUpdatePhone(@RequestBody PhoneDto phone){
			System.out.println(getUserName());
			 userService.createOrUpdatePhone(phone , getUserName());
			 
			 return phone;
		}
	
		public void delete(){}
	
	
}
