package net.endpoint.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.account.service.UserService;
import net.endpoint.emailtemplate.service.EmailService;

@RestController
@RequestMapping("/")
public class HomeController {
		@Autowired
		UserService userService;
		@Autowired
		EmailService emailService;
		
		public void setUserService(UserService userService){
			this.userService = userService;
		}
		
	@RequestMapping(method = RequestMethod.GET)
	public String get(){
	return "Endpoint Api";
	}
	
	
	
	

}
