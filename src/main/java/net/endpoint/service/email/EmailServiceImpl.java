package net.endpoint.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import 	org.springframework.mail.javamail.MimeMessageHelper;

public class EmailServiceImpl implements EmailService {
	@Autowired
	JavaMailSenderImpl sender;
	
	public void setSender(JavaMailSenderImpl sender) {
		this.sender = sender;
	}

	@Override
	public void sentEmail() {
	
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo("betwar512@gmail.com");
			helper.setText("Thank you for ordering!");
		 } catch (MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);
	}

}
