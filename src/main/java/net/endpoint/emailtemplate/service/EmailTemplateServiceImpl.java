package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
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
	    private TemplateEngine stringTemplateEngine;
	    @Autowired
	    private TemplateEngine htmlTemplateEngine;
	    @Autowired
	    private EmailTemplateDao emailTemplateDao;


	    /*-------------------------------------------------------*/
	    /*		               Template Methods 			    */
	    /*-------------------------------------------------------*/
	    

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
	        final Context ctx = new Context(null);
	         //Attach filed contents to context       
	        etd.getFields().forEach(f->{
	        	    ctx.setVariable(f.getFiledId(), f.getContent());   	
	        });

	        // Create the HTML body using Thymeleaf
	        final String output = stringTemplateEngine.process(etd.getHtmlContent(), ctx);
	        message.setText(output, true /* isHtml */);
	        //Attach Files here 
	      if(  etd.getAttachments() != null){ 
	        etd.getAttachments().forEach(m->{ 	
	        	   // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"  
			 try {			 
					InputStreamSource	imageSource = new ByteArrayResource(m.getAttachedFile());
					message.addInline(m.getHtmlFiledId(), imageSource, m.getFileType());
				} catch (Exception e ) {
					logger.error(e);
				}	
	        });
	       }
	        // Send mail
	      this.mailSender.send(mimeMessage);

	    }
	    
	    

	    
	    
		@Override
		public void generateEmail(EmailTemplateDto eto,SendEmailDto emailDto) throws MessagingException {

		    // Prepare the evaluation context
	        final Context ctx = new Context(emailDto.getLocale());
	        ctx.setVariable("name", emailDto.toString());
	        // so that we can reference it from HTML  	
	        eto.getAttachments().forEach(t->ctx.setVariable(t.getHtmlFiledId() , t.getName()));
	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	        final MimeMessageHelper message
	            = new MimeMessageHelper(mimeMessage, true /* multipart */,EmailVariables.EmailContentProperties.ENCODE_UTF_8.getValue());
	        message.setSubject(emailDto.getSubject());
	        message.setFrom(emailDto.getFrom());
	        message.setTo(emailDto.getTo());

	        // Create the HTML body using Thymeleaf
	        final String htmlContent = this.stringTemplateEngine.process(eto.getHtmlContent(), ctx);
	        message.setText(htmlContent, true /* isHtml */);

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
		

		@Override
		@Transactional
		public List<EmailTemplateDto> getAllTemplates() {
			List<EmailTemplate> templates = this.emailTemplateDao.getAll();
			 return templates.stream().map(EmailTemplate::pars).collect(Collectors.toList());
		}



		public void setEmailTemplateDao(EmailTemplateDao emailTemplateDao) {
			this.emailTemplateDao = emailTemplateDao;
		}
	    
		
		   private static final String url =  "templates/welcometemplate/";

		    private static final String EMAIL_TEXT_TEMPLATE_NAME = "text/email-text";
		    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "html/email-simple";
		    private static final String EMAIL_WITHATTACHMENT_TEMPLATE_NAME = "html/email-withattachment";
		    private static final String EMAIL_INLINEIMAGE_TEMPLATE_NAME = "html/email-inlineimage";
		    private static final String EMAIL_EDITABLE_TEMPLATE_CLASSPATH_RES =  "classpath:"+url+"welcome.html";
		    private static final String BACKGROUND_IMAGE = "mail/editablehtml/images/background.png";
		    private static final String LOGO_BACKGROUND_IMAGE = "mail/editablehtml/images/logo-background.png";
		    private static final String THYMELEAF_BANNER_IMAGE = "mail/editablehtml/images/thymeleaf-banner.png";
		    private static final String THYMELEAF_LOGO_IMAGE = "mail/editablehtml/images/thymeleaf-logo.png";

		    @Autowired
		    private ApplicationContext applicationContext;

		    /* 
		     * Send HTML mail (simple) 
		     */
		    public void sendSimpleMail(
		        final String recipientName, final String recipientEmail, final Locale locale)
		        throws MessagingException {

		        // Prepare the evaluation context
		        final Context ctx = new Context(locale);
		        ctx.setVariable("name", recipientName);
		        ctx.setVariable("subscriptionDate", new Date());
		        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

		        // Prepare message using a Spring helper
		        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, EmailVariables.EmailContentProperties.ENCODE_UTF_8.getValue());
		        message.setSubject("Example HTML email (simple)");
		        message.setFrom("info@skinqualitycare.com.au");
		        message.setTo(recipientEmail);

		        // Create the HTML body using Thymeleaf
		        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
		        message.setText(htmlContent, true /* isHtml */);

		        // Send email
		        this.mailSender.send(mimeMessage);
		       }

		    /* 
		     * Send HTML mail with attachment. 
		     */
		    public void sendMailWithAttachment(
		        final String recipientName, final String recipientEmail, final String attachmentFileName, final String attachmentContentType, final Locale locale)
		        throws MessagingException {

		        // Prepare the evaluation context
		        final Context ctx = new Context(locale);
		        ctx.setVariable("name", "Tests");
		        ctx.setVariable("subscriptionDate", new Date());
		        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

		        // Prepare message using a Spring helper
		        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		        final MimeMessageHelper message
		            = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
		        message.setSubject("Example HTML email with attachment");
		        message.setFrom("info@skinqualitycare.com.au");
		        message.setTo("betwar512@gmail.com");

		        // Create the HTML body using Thymeleaf
		        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE_NAME, ctx);
		        message.setText(htmlContent, true /* isHtml */);

//		        // Add the attachment
//		        final InputStreamSource attachmentSource = new ByteArrayResource(attachmentBytes);
//		        message.addAttachment(
//		            attachmentFileName, attachmentSource, attachmentContentType);

		        // Send mail
		        this.mailSender.send(mimeMessage);
		    }


		    /* 
		     * Send HTML mail with in-line image
		     */
		    public void sendMailWithInline(
		        final String recipientName, final String recipientEmail, final String imageResourceName,
		        final byte[] imageBytes, final String imageContentType, final Locale locale)
		        throws MessagingException {

		        // Prepare the evaluation context
		        final Context ctx = new Context(locale);
		        ctx.setVariable("name", recipientName);
		        ctx.setVariable("subscriptionDate", new Date());
		        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		        ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

		        // Prepare message using a Spring helper
		        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		        final MimeMessageHelper message
		            = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
		        message.setSubject("Example HTML email with inline image");
		        message.setFrom("info@skinqualitycare.com.au");
		        message.setTo(recipientEmail);

		        // Create the HTML body using Thymeleaf
		        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_INLINEIMAGE_TEMPLATE_NAME, ctx);
		        message.setText(htmlContent, true /* isHtml */);

		        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
		        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
		        message.addInline(imageResourceName, imageSource, imageContentType);

		        // Send mail
		        this.mailSender.send(mimeMessage);
		    }

		    
		    
		    
		    

		    /* 
		     * Send HTML mail with inline image
		     */
		    public String getEditableMailTemplate() throws IOException {
		        final Resource templateResource = this.applicationContext.getResource(EMAIL_EDITABLE_TEMPLATE_CLASSPATH_RES);
		        final InputStream inputStream = templateResource.getInputStream();
		        return IOUtils.toString(inputStream, EmailContentProperties.ENCODE_UTF_8.getValue());
		    }


		
		
		
	
}
