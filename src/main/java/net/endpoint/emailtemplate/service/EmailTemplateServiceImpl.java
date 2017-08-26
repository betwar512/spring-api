package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import net.endpoint.emailtemplate.dao.EmailTemplateDao;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;
import net.endpoint.emailtemplate.model.EmailTemplate;
import net.endpoint.utils.enums.EmailTemplates;

@Service
public class EmailTemplateServiceImpl  implements EmailTemplateService{

	  Logger logger = Logger.getLogger(EmailTemplateServiceImpl.class);
	   
	    @Autowired
	    private JavaMailSender mailSender;

	    @Autowired
	    private TemplateEngine stringTemplateEngine;

	    @Autowired
	    private EmailTemplateDao EmailTemplateDao;

	


	    
	    
	    /*-------------------------------------------------------*/
	    /*		               Template Methods 			    */
	    /*-------------------------------------------------------*/
	    

 public void sendEditableTemplateEmail(EmailTemplateDto etd,SendEmailDto emailDto) throws MessagingException, IOException{
	    	
	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	        final MimeMessageHelper message
            = new MimeMessageHelper(mimeMessage, true, EmailTemplates.EmailContentProperties.ENCODE_UTF_8.getValue());
	        message.setSubject(emailDto.getSubject());
	        message.setFrom(emailDto.getFrom());
	        message.setTo(emailDto.getTo());

	        // Prepare the evaluation context
	        final Context ctx = new Context(null);
	         //Attach filed contents to context       
	        etd.getFields().forEach(f->{
	        	    ctx.setVariable(f.getFiledId(), f.getContent());   	
	        });

	        // Create the HTML body using Thymeleaf
	        final String output = stringTemplateEngine.process(etd.getHtmlContent(), ctx);
	        message.setText(output, true /* isHtml */);
	        //Attach Files here 
	        etd.getAttachments().forEach(m->{ 	
	        	   // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"  
			 try {
					InputStreamSource	imageSource = new ByteArrayResource(m.getAttachedFile().getBytes());
					message.addInline(m.getHtmlFiledId(), imageSource, m.getFileType());
				} catch (Exception e ) {
					logger.error(e);
					e.printStackTrace();
				}	
	        });
 
	        // Send mail
	      this.mailSender.send(mimeMessage);

	    }
	    
	    

	    
	    
		@Override
		public void generateEmail(EmailTemplateDto eto,SendEmailDto emailDto) throws MessagingException {

		    // Prepare the evaluation context
	        final Context ctx = new Context(emailDto.getLocale());
	        ctx.setVariable("name", emailDto.toString());
	        ctx.setVariable("subscriptionDate", new Date());
	        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));	        
	        eto.getAttachments().forEach(t->{    	
	        	  ctx.setVariable(t.getHtmlFiledId(), t.getName()); // so that we can reference it from HTML  	
	        });
	        
	      

	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	        final MimeMessageHelper message
	            = new MimeMessageHelper(mimeMessage, true /* multipart */,EmailTemplates.EmailContentProperties.ENCODE_UTF_8.getValue());
	        message.setSubject(emailDto.getSubject());
	        message.setFrom(emailDto.getFrom());
	        message.setTo(emailDto.getTo());

	        // Create the HTML body using Thymeleaf
	        final String htmlContent = this.stringTemplateEngine.process(eto.getHtmlContent(), ctx);
	        message.setText(htmlContent, true /* isHtml */);

	        eto.getAttachments().forEach(m->{ 	
	        	   // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
		        
				try {
					InputStreamSource	imageSource = new ByteArrayResource(m.getAttachedFile().getBytes());
					message.addInline(m.getHtmlFiledId(), imageSource, m.getFileType());
				} catch (Exception e ) {
					logger.error(e);
				}
   	
	        });
	        
	     
	        // Send mail
	        this.mailSender.send(mimeMessage);
			
			
			
		}

		@Override
		@Transactional
		public List<EmailTemplateDto> getAllTemplates() {
			List<EmailTemplate> templates =   this.EmailTemplateDao.getAll();
			 return templates.stream().map(f->f.pars()).collect(Collectors.toList());
		}



		public void setEmailTemplateDao(EmailTemplateDao emailTemplateDao) {
			EmailTemplateDao = emailTemplateDao;
		}
	    
	
}
