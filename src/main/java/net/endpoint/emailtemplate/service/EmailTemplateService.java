package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.RecivedEmailDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;

/**
 * Services For EmailTemplate provided by themeleaf Engine  
 * @author Betwar-mac
 *
 */
public interface EmailTemplateService {

	public List<EmailTemplateDto> getAllTemplates();
	public void generateEmail(EmailTemplateDto eto,SendEmailDto emailDto) throws MessagingException;
	public void sendEditableTemplateEmail(EmailTemplateDto etd,RecivedEmailDto emailDto) throws MessagingException, IOException;
	
}
