package net.endpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.dto.account.EmailDto;
import net.endpoint.service.email.EmailService;
import net.endpoint.service.email.EmailTemplateServiceImpl;


@RestController
@RequestMapping("/api/email")
public class EmailController extends MainController {

	
	@Autowired
	EmailService emailService;
	@Autowired
	EmailTemplateServiceImpl etp;
	
	public List<EmailDto> getAll(){

		return emailService.checkEmails(this.loadUser());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String sendEmail() {
		return "";
	}
	
	
	
}