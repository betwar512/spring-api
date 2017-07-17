package net.endpoint.config;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SpringApplication {

  @Bean
  public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
	        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	        ppc.setLocation(new ClassPathResource("application.properties"));
	        ppc.setIgnoreUnresolvablePlaceholders(true);
	        return ppc;
	    }
	
  
	
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
