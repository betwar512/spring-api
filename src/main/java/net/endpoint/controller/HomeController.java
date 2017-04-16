package net.endpoint.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.model.User;
import net.endpoint.service.UserService;

@RestController
@RequestMapping("/")
public class HomeController {
		@Autowired
		UserService userService;
		
		public void setUserService(UserService userService){
			this.userService = userService;
		}
		
	@GetMapping
	@Transactional
	public List<User> get(){
			List<User> result = this.userService.getAll();

		return result;
	}
	

	
	
}
