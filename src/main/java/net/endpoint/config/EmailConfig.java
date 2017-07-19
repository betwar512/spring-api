package net.endpoint.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@EnableIntegration
public class EmailConfig {

	// @Autowired
	// DirectChannel outboundMail;

//	@Bean
//	@Transformer(inputChannel="inboundMail", outputChannel="outboundMail")
//	public org.springframework.integration.transformer.Transformer transformer() {
//	    return new MailToStringTransformer();
//	}
	 @Bean
	   public JavaMailSenderImpl mailSender() {
	       JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	   	mailSender.setHost("email-smtp.us-east-1.amazonaws.com");
			mailSender.setProtocol("smtp");
			mailSender.setPort(25);
			mailSender.setUsername("AKIAI7A26VJDLF4OJQNA");
			mailSender.setPassword("AhRoDI/aM9jAGkGKYSor9Yjc1qL+ykqLOBAIN0pMJqbA");

		     Properties mailProperties = new Properties();
		        mailProperties.put("mail.smtp.auth", "true");
		        mailProperties.put("mail.smtp.starttls.enable", "true");
		        mailProperties.put("mail.smtp.starttls.required", "true");
		        mailProperties.put("mail.smtp.debug", "true");
		        mailProperties.put("mail.transport.protocol", "smtps");
		        mailSender.setJavaMailProperties(mailProperties);
	       return mailSender;
	   }
	
}
