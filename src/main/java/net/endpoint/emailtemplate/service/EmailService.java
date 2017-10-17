package net.endpoint.emailtemplate.service;

import java.util.List;
import javax.mail.MessagingException;
import net.endpoint.account.model.User;
import net.endpoint.emailtemplate.dto.RecivedEmailDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;

/**
 * 
 * @author Betwar-mac
 *
 */
public interface EmailService {

	/**
	 * <p>Send Email by using EmailDto object </p>
	 * @param emailDto
	 */
	public void sendEmail(SendEmailDto emailDto);
	
	/**
	 * <p>Using SimpleEmail to sned simple String email </p>
	 * @param to
	 * @param subject
	 * @param text
	 */
	void sendSimpleMail(String to, String subject, String text);
	/**
	 * <p>Get list of user e-mails </p>
	 * @param user
	 * @return  List<EmailDto>
	 */
	public List<RecivedEmailDto> checkEmails(User user);
	/**
	 * <p>Send Html email include attachments </p>
	 * @param user
	 * @param subject
	 * @param to
	 * @param htmlText
	 * @throws MessagingException
	 */
	public void sendHtmlEmailWithAttachment(User user, String subject, String to, String htmlText) throws MessagingException;

}
