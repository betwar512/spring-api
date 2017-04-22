package net.endpoint.controller;


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

		@RequestMapping(method = RequestMethod.GET)
		public User get(@RequestParam(value="id") String id){
			long idLong = Long.parseLong(id);
			User   user = this.userService.get(idLong);
		    return user;
		}
		
		@RequestMapping(value="/create",method = RequestMethod.POST)
		public void create(@RequestBody User user){

		}
		
		public void update(){}
		
		public void delete(){}
	
	
}
