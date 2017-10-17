package net.endpoint.emailtemplate;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.RecivedEmailDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;
import net.endpoint.emailtemplate.service.EmailService;
import net.endpoint.emailtemplate.service.EmailTemplateService;
import net.endpoint.main.MainController;
import net.endpoint.utils.enums.EmailVariables;


@RestController
@RequestMapping("/api/email")
public class EmailController extends MainController {

	
	@Autowired
	EmailService emailService;
	@Autowired
	EmailTemplateService etp;
	
	@RequestMapping(path="/getEmails",method = RequestMethod.GET)
	public List<RecivedEmailDto> getAll(){
	return emailService.checkEmails(this.loadUser());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void sendEmail(SendEmailDto emailDto) {	
		 this.emailService.sendEmail(emailDto);
	}
	
	@RequestMapping(path="/getTemplates",method = RequestMethod.GET)
	public List<EmailTemplateDto> getTemplates(){
			 return this.etp.getAllTemplates();
	}
	
	@RequestMapping(path="/sendEmailTemplate",method = RequestMethod.POST)
	public void sendEmailTemplate(EmailTemplateDto etDto,SendEmailDto emailDto){
		
		try {
			this.etp.sendEditableTemplateEmail(etDto, emailDto);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(path="/sendWelcomeEmail",method = RequestMethod.POST)
	public void sendWelcomeEmail(SendEmailDto emailDto){
	if(this.isAdmin()){
	   try {
		   EmailTemplateDto myTemplate =  etp.getTemplate(EmailVariables.EmailTemplatesUrls.WELCOME_EMAIL.getKey());
		   this.etp.sendEditableTemplateEmail(myTemplate, emailDto); 
	        } catch (NotFoundException | MessagingException | IOException e) {
		   e.printStackTrace();
	     }
	   }
	 }
	
}
