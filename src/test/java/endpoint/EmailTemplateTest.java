package endpoint;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import net.endpoint.account.dao.ProfileDao;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.User;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;
import net.endpoint.emailtemplate.model.EmailTemplate;
import net.endpoint.emailtemplate.service.EmailTemplateService;
import net.endpoint.utils.enums.EmailVariables;


public class EmailTemplateTest extends DatabaseTest {
	@Autowired
	ProfileDao profileDao;
//	@Autowired
	EmailTemplateService emts;
	
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine htmlTemplateEngine;
	
	@Test
	public void test() {
	EmailTemplate et = this.sessionFactory.getCurrentSession().load(EmailTemplate.class, 1l);
		EmailTemplateDto edto=et.pars();
		
		User user = this.sessionFactory.getCurrentSession().load(User.class, 5l);
		 this.mailSender = this.getJavaMailSender(user.getEmail(), "4650080");
		
		SendEmailDto emailDto = new SendEmailDto("info@skinqualitycare.com.au","michael.safaie@yahoo.com.au",
												 "Welcome To System","","Michael","4650080",null, new Date(),null,null);
		try {
			this.sendEditableTemplateEmail(edto, emailDto);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Person u = profileDao.findByUserName("info@skinqualitycare.com.au");
//		EmailTemplateDto etd = new EmailTemplateDto("", "Test Send", new ArrayList<>(), new ArrayList<>());
		assertNotNull(u);
		//byte[] file =  etd.getAttachments().get(0).getAttachedFile();
	}

	
	 public JavaMailSender getJavaMailSender(String email,String password) {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();	 
		    mailSender.setHost("smtp.skinqualitycare.com.au");
		    mailSender.setPort(25);	     
		    mailSender.setUsername(email);
		    mailSender.setPassword(password);
		     
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
//		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		     
		    return mailSender;
		}
	
	
	 public void sendEditableTemplateEmail(EmailTemplateDto etd,SendEmailDto emailDto)
			 throws MessagingException ,
			        IOException{

		        // Prepare message using a Spring helper
		        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		        final MimeMessageHelper message
	            = new MimeMessageHelper(mimeMessage, true, EmailVariables.EmailContentProperties.ENCODE_UTF_8.getValue());
		        message.setSubject(emailDto.getSubject());
		        message.setFrom(emailDto.getFrom());
		        message.setTo(emailDto.getTo());
		        
		        message.setCc("betwar512@gmail.com");
		        // Prepare the evaluation context
		        final Context ctx = new Context(emailDto.getLocale());
		        
		        ctx.setVariable("email", emailDto.getTo());  
		        ctx.setVariable("username", emailDto.getUserName());
		        
		         //Attach filed contents to context       
		        etd.getFields().forEach(f->{
		        	    ctx.setVariable(f.getFiledId(), f.getContent());   
		        });

		        // Create the HTML body using Thymeleaf
		        final String htmlContent = this.htmlTemplateEngine.process(etd.getHtmlContent(), ctx);
		        message.setText(htmlContent, true /* isHtml */);
		        //Attach Files here 
		      if(  etd.getAttachments() != null){ 
		        etd.getAttachments().forEach(m->{ 	
		        	   // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"  
				 try {			 
						InputStreamSource	imageSource = new ByteArrayResource(m.getAttachedFile());
						message.addInline(m.getHtmlFiledId(), imageSource, m.getFileType());
					} catch (Exception e ) {
						
					}	
		        });
		       }
		        // Send mail
		     // this.mailSender.send(mimeMessage);

		    }
	
	private void readFile(){}
	
	private void writeToString(){}
	
	
	
	
	
	
}
