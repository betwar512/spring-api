package net.endpoint.controller;


import java.security.Principal;
import java.util.Set;
import java.util.stream.Stream;

import javax.management.relation.RoleStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import net.endpoint.service.email.EmailServiceImpl;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/user")
public class UserController extends MainController {

	
		@Autowired
		UserService userService;

		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		@RequestMapping(method = RequestMethod.GET)
		public ProfileDto get(Principal principal){
			EmailService service = new EmailServiceImpl();
			service.sentEmail();

			String name = principal.getName();
    	 ProfileDto dto = loadProfile(name);
    	 System.out.println(dto.toString());
    	 return dto;
		}
		
		@RequestMapping(value="/create",method = RequestMethod.POST)
		public AccountRequestDto create(@RequestBody AccountRequestDto accountRequestDto,Principal principal){
			
			  User user =  userService.findByName(principal.getName());
			   Set<SecurityRole> roles = user.getRolse();
			   Stream<SecurityRole> result = roles.stream().filter(t-> t.getLevel() > 4);
			   
		 if(result.count() > 0){  
			   this.userService.createAccount(accountRequestDto);
			}
			return new AccountRequestDto();
		}
		
		
		@RequestMapping(value="/update",method = RequestMethod.POST)
		public ProfileDto update(@RequestBody ProfileDto profile,Principal principal){
			String name = principal.getName();
			profile.setEmail(name);
			this.userService.updateProfile(profile);
			return profile;
		}
		
		@RequestMapping(value="/update/address",method = RequestMethod.POST)
		public AddressDto createOrUpdateAddress(@RequestBody AddressDto address,Principal principal){
			 userService.createOrUpdateAddress(address);
			 return address;
		}
		
	
		public void delete(){}
	
	
}
