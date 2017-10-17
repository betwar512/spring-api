package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javassist.NotFoundException;
import net.endpoint.emailtemplate.dao.EmailTemplateDao;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;
import net.endpoint.emailtemplate.model.EmailTemplate;
import net.endpoint.utils.enums.EmailVariables;
import net.endpoint.utils.enums.EmailVariables.EmailContentProperties;

@Service
public class EmailTemplateServiceImpl  implements EmailTemplateService{

	  Logger logger = Logger.getLogger(EmailTemplateServiceImpl.class);

	    @Autowired
	    private JavaMailSender mailSender;
	    @Autowired
	    private TemplateEngine htmlTemplateEngine;
	    @Autowired
	    private EmailTemplateDao emailTemplateDao;


	    /*-------------------------------------------------------*/
	    /*		               Template Methods 			    */
	    /*-------------------------------------------------------*/
	    
	 private Context createDefaultContext(SendEmailDto emailDto){
		 final Context ctx = new Context(emailDto.getLocale());
		 ctx.setVariable("email", emailDto.getTo());
		 ctx.setVariable("username", emailDto.getUserName());
		 ctx.setVariable("timestamp", new Date());
		 return ctx; 
	 }   

   @Override
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
	        

	        // Prepare the evaluation context
	        final Context ctx = createDefaultContext(emailDto);  
	         //Attach filed contents to context       
	        etd.getFields().forEach(f->{
	        	    ctx.setVariable(f.getFiledId(), f.getContent());   	
	           });
	        

	        // Create the HTML body using Thymeleaf
	        final String output = htmlTemplateEngine.process(etd.getHtmlContent(), ctx);
	        message.setText(output, true /* isHtml */);
	        //Attach Files here 
	      
	           etd.getAttachments().forEach(m->{ 	
	        	   // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"  
			   try {			 
					InputStreamSource	imageSource = new ByteArrayResource(m.getAttachedFile());
					message.addInline(m.getHtmlFiledId(), imageSource, m.getFileType());
			    	} catch (Exception e ) {
				  	logger.error(e);
			    	}	
	         });

	        // Send mail
	      this.mailSender.send(mimeMessage);

	    }
	    
	    

	    
	    
		@Override
		public void generateEmail(EmailTemplateDto eto,SendEmailDto emailDto) throws MessagingException {

		    // Prepare the evaluation context
	        final Context ctx = new Context(emailDto.getLocale());
	        // so that we can reference it from HTML  	
	        if(eto.getFields() != null ){        
	             eto.getFields().forEach(t-> ctx.setVariable(t.getFiledId() ,t.getContent()));
	           }
	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.getJavaMailSender(emailDto.getFrom(),emailDto.getPassword()).createMimeMessage();
	        final MimeMessageHelper message
	            = new MimeMessageHelper(mimeMessage, true /* multipart */,EmailVariables.EmailContentProperties.ENCODE_UTF_8.getValue());
	        message.setSubject(emailDto.getSubject());
	        message.setFrom(emailDto.getFrom());
	        message.setTo(emailDto.getTo());

	        // Create the HTML body using Thymeleaf
	        final String htmlContent = this.htmlTemplateEngine.process(eto.getHtmlContent(), ctx);
	        message.setText(htmlContent, true);

	        eto.getAttachments().forEach(m->{ 	
	        	   // Add the in-line image, referenced from the HTML code as "cid:${imageResourceName}"
		        
				try {
					InputStreamSource	imageSource = new ByteArrayResource(m.getAttachedFile());
					message.addInline(m.getHtmlFiledId(), imageSource, m.getFileType());
				} catch (Exception e ) {
					logger.error(e);
				}
   	
	        });
	        
	     
	        // Send mail
	      this.mailSender.send(mimeMessage);
	 }
		
		
		public void generateWelcomeEmail(){
			
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
		

		@Override
		@Transactional
		public List<EmailTemplateDto> getAllTemplates() {
			List<EmailTemplate> templates = this.emailTemplateDao.getAll();
			 return templates.stream().map(EmailTemplate::pars).collect(Collectors.toList());
		}



		public void setEmailTemplateDao(EmailTemplateDao emailTemplateDao) {
			this.emailTemplateDao = emailTemplateDao;
		}
	    
		
	
		    @Autowired
		    private ApplicationContext applicationContext;

		    
		    /* 
		     * Send HTML mail with inline image
		     */
		    public String getEditableMailTemplate(String url) throws IOException {
		        final Resource templateResource = this.applicationContext.getResource(url);
		        final InputStream inputStream = templateResource.getInputStream();
		        return IOUtils.toString(inputStream, EmailContentProperties.ENCODE_UTF_8.getValue());
		    }

			@Override
			public EmailTemplateDto getTemplate(String templateName) throws NotFoundException {
				List<EmailTemplate> list = this.emailTemplateDao.findByName(templateName);
				if(list.isEmpty()){
					throw new NotFoundException("This template dont exist in databse.");
				    }
				EmailTemplate template = list.get(0);
			 return template.pars();
			}


		
		
		
	
}
