package net.endpoint.service.email;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import 	org.springframework.mail.javamail.MimeMessageHelper;


public class EmailServiceImpl implements EmailService {

    private	JavaMailSenderImpl mailSender;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public EmailServiceImpl(){
	    this.mailSender();
	}
	

  private void mailSender() {
	        this.mailSender = new JavaMailSenderImpl();
	        this.mailSender.setHost("email-smtp.us-east-1.amazonaws.com");
	        this.mailSender.setProtocol("smtp");
	        this.mailSender.setPort(25);
	        this.mailSender.setUsername("AKIAI7A26VJDLF4OJQNA");
	        this.mailSender.setPassword("AhRoDI/aM9jAGkGKYSor9Yjc1qL+ykqLOBAIN0pMJqbA");
		Properties mailProperties = new Properties();
		        mailProperties.put("mail.smtp.auth", "true");
		        mailProperties.put("mail.smtp.starttls.enable", "true");
		        mailProperties.put("mail.smtp.starttls.required", "true");
		        mailProperties.put("mail.smtp.debug", "true");
		        mailProperties.put("mail.transport.protocol", "smtps");
		        mailSender.setJavaMailProperties(mailProperties);
	   }


	@Override
	public void sentEmail() {
	if(this.mailSender != null){
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
	   try {
			helper.setFrom("admin@skinqualitycare.com.au");
			helper.setTo("betwar512@gmail.com");
			helper.setText("Thank you for ordering!");
		
		  } catch (MessagingException e) {
			e.printStackTrace();
		}
	//	mailSender.send(message);
	  }else
			System.out.println("Its null ");
	}

}
