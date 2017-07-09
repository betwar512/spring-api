package net.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.service.UserService;
import net.endpoint.service.email.EmailService;
import net.endpoint.service.email.EmailServiceImpl;

@RestController
@RequestMapping("/")
public class HomeController {
		@Autowired
		UserService userService;
		
		public void setUserService(UserService userService){
			this.userService = userService;
		}
		
	@RequestMapping(method = RequestMethod.GET)
	public String get(){
	EmailService service = new EmailServiceImpl();
	service.sentEmail();
	return "Endpoint Api";
	}
	
	
	
	

}
