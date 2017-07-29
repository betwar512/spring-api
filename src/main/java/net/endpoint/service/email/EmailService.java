package net.endpoint.service.email;

import java.util.List;

import net.endpoint.dto.account.EmailDto;
import net.endpoint.model.User;

public interface EmailService {

	void sentEmail(String to, String subject, String text);
	void sendSimpleMail(String to, String subject, String text);
	public List<EmailDto> checkEmails(User user);

}
