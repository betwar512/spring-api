package net.endpoint.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

		@RequestMapping(value="/api/user",method = RequestMethod.GET)
		public User get(Principal principal){
			
			String name=  principal.getName();
			System.out.println("name"+name);
			 return userService.findByName(name);
			
	
		}
		
		@RequestMapping(value="/create",method = RequestMethod.POST)
		public void create(@RequestBody User user){

			
		}
		
		public void update(){}
		
		public void delete(){}
	
	
}
