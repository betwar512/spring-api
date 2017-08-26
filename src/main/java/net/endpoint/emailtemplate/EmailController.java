package net.endpoint.emailtemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.emailtemplate.dto.RecivedEmailDto;
import net.endpoint.emailtemplate.service.EmailService;
import net.endpoint.emailtemplate.service.EmailTemplateServiceImpl;
import net.endpoint.main.MainController;


@RestController
@RequestMapping("/api/email")
public class EmailController extends MainController {

	
	@Autowired
	EmailService emailService;
	@Autowired
	EmailTemplateServiceImpl etp;
	
	public List<RecivedEmailDto> getAll(){

		return emailService.checkEmails(this.loadUser());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String sendEmail() {
		return "";
	}
	
	
	
}
