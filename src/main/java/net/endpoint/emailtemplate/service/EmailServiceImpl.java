package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import 	org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import net.endpoint.account.model.User;
import net.endpoint.emailtemplate.dto.EmailDto;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
    private	JavaMailSender mailSender;



	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	
	
	

	@Override
    public void sendSimpleMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage(); 
		 message.setTo(to); 
		 message.setSubject(subject); 
		 message.setText(text);
		 mailSender.send(message);
		}
	
	@Override
	public void sentEmail(String to,String subject,String text) {
	if(this.mailSender != null){
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
	   try {
			helper.setFrom("admin@skinqualitycare.com.au");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			mailSender.send(message);
		  } catch (MessagingException e) {
			e.printStackTrace();
		}
	  }else
			System.out.println("Its null ");
	}

	@Override
	public List<EmailDto> checkEmails(User user)  {
	      List<EmailDto> emails = new ArrayList<>();
		try {  
		    	  Properties  props  = new Properties();
		    	  String       host  = "mail.skinqualitycare.com.au";
		    	  String   username  = user.getEmail();
		    	  String   password  = user.getPassword();
		    	  String       port  = "993";
		    	  String   provider  = "imap";
		    	    props.put("mail.imap.host", host);
		    	    props.put("mail.imap.port", port);
		    	    props.put("mail.imap.ssl.enable", "true");
		    	      //Connect to the server
		    	  Session session = Session.getDefaultInstance(props);
		    	   Store    store = session.getStore(provider);     
		    	            store.connect(host, username, password);


		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_ONLY);

		      // retrieve the messages from the folder in an array and print it
		      Message[] messages = emailFolder.getMessages();
		
		      System.out.println("messages.length---" + messages.length);
		
		      for (int i = 0, n = messages.length; i < n; i++) {
		         Message message = messages[i];
		         System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		         System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("Text: " + message.getContent().toString());

		         emails.add(new EmailDto(message.getFrom()[0].toString()
							        		 ,message.getSubject()
							        		 ,message.getContent().toString()
							        		 ,message.getSentDate()));
		         
		      }

		        //close the store and folder objects
		        emailFolder.close(false);
		        store.close();
		    	return emails;
			    } catch (MessagingException | IOException e) {
				e.printStackTrace();
				return emails;
			}
		   

		}
	
	@Override
	public void sendHtmlEmailWithAttachment(User user,String subject,String to,String htmlText) throws MessagingException{
		
		   Properties props = new Properties();
	        props.setProperty("mail.transport.protocol", "smtp");
	        props.setProperty("mail.host", "smtp.mymailserver.com");
	        props.setProperty("mail.user", user.getEmail());
	        props.setProperty("mail.password", user.getPassword());

	        Session mailSession = Session.getDefaultInstance(props, null);
	        mailSession.setDebug(true);
	        Transport transport = mailSession.getTransport();
	        MimeMessage message = new MimeMessage(mailSession);
	        message.setSubject(subject);
	        message.setFrom(new InternetAddress(user.getEmail()));
	        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	        // This HTML mail have  2 part, the BODY and the embedded image
	        MimeMultipart multipart = new MimeMultipart("related");
	        // first part  (the html)
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(htmlText, "text/html");
	        // add it
	        multipart.addBodyPart(messageBodyPart);
	        
	        // second part (the image)
	        messageBodyPart = new MimeBodyPart();
	        
	        DataSource fds = new FileDataSource("C:\\images\\jht.gif");
	        DataHandler dh = new DataHandler(fds);      
	        messageBodyPart.setDataHandler(dh);
	        messageBodyPart.setHeader("Content-ID","<image>");

	        // add it
	        multipart.addBodyPart(messageBodyPart);

	        // put everything together
	        message.setContent(multipart);

	        transport.connect();
	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        transport.close();
	        }
	

	
}
