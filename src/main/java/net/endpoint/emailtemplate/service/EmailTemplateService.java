package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;

import javassist.NotFoundException;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;

/**
 * Services For EmailTemplate provided by themeleaf Engine  
 * @author Betwar-mac
 *
 */
public interface EmailTemplateService {

	public List<EmailTemplateDto> getAllTemplates();
	public void generateEmail(EmailTemplateDto eto,SendEmailDto emailDto) throws MessagingException;
	public void sendEditableTemplateEmail(EmailTemplateDto etd,SendEmailDto emailDto) throws MessagingException, IOException;
	public EmailTemplateDto getTemplate(String templateName) throws NotFoundException;
}
