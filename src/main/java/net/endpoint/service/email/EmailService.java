package net.endpoint.service.email;

import java.util.List;

import javax.mail.MessagingException;

import net.endpoint.dto.account.EmailDto;
import net.endpoint.model.User;

public interface EmailService {
	/**
	 * <p>Send simple email from Admin email </p>
	 * @param to
	 * @param subject
	 * @param text
	 */
	void sentEmail(String to, String subject, String text);
	/**
	 * 
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
	public List<EmailDto> checkEmails(User user);
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
