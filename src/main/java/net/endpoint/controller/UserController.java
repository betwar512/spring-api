package net.endpoint.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

	
		@Autowired
		UserService userService;
	
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		@RequestMapping(method = RequestMethod.GET)
		public ProfileDto get(Principal principal){
			String name = principal.getName();
    	 ProfileDto dto = userService.findByUserName(name);
    	 System.out.println(dto.toString());
    	 return dto;
		}
		
		@RequestMapping(value="/create",method = RequestMethod.POST)
		public AccountRequestDto create(@RequestBody AccountRequestDto accountRequestDto){
			   this.userService.createAccount(accountRequestDto);
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
		public AddressDto createOrUpdateAddress(@RequestBody AddressDto address){
			return userService.createOrUpdateAddress(address);
		}
		
	
		public void delete(){}
	
	
}
