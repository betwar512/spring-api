package net.endpoint.service.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import net.endpoint.config.SpringMailConfig;

@Service
public class EmailTemplateServiceImpl {
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
	    

	    private static final String product1 =url+"product-1.png";
	    private static final String product2 = url+"product-2.png";
	    private static final String product3 = url+"product-3.png";
	    private static final String product4 = url+"product-4.png";
	    private static final String PNG_MIME = "image/png";

	    @Autowired
	    private ApplicationContext applicationContext;

	    @Autowired
	    private JavaMailSender mailSender;

	    @Autowired
	    private TemplateEngine htmlTemplateEngine;

	    @Autowired
	    private TemplateEngine textTemplateEngine;

	    @Autowired
	    private TemplateEngine stringTemplateEngine;



	    /* 
	     * Send plain TEXT mail 
	     */
	    public void sendTextMail(
	        final String recipientName, final String recipientEmail, final Locale locale)
	        throws MessagingException {
	    	 Locale l = new Locale("en");
	        // Prepare the evaluation context
	        final Context ctx = new Context(locale);
	        ctx.setVariable("name", recipientName);
	        ctx.setVariable("subscriptionDate", new Date());
	        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
	        message.setSubject("Example plain TEXT email");
	        message.setFrom("info@skinqualitycare.com.au");
	        message.setTo(recipientEmail);

	        // Create the plain TEXT body using Thymeleaf
	        final String textContent = this.textTemplateEngine.process(EMAIL_TEXT_TEMPLATE_NAME, ctx);
	        message.setText(textContent);

	        // Send email
	        this.mailSender.send(mimeMessage);
	    }


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
	        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
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
	        final String recipientName, final String recipientEmail, final String attachmentFileName,
	        final byte[] attachmentBytes, final String attachmentContentType, final Locale locale)
	        throws MessagingException {

	        // Prepare the evaluation context
	        final Context ctx = new Context(locale);
	        ctx.setVariable("name", recipientName);
	        ctx.setVariable("subscriptionDate", new Date());
	        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	        final MimeMessageHelper message
	            = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
	        message.setSubject("Example HTML email with attachment");
	        message.setFrom("info@skinqualitycare.com.au");
	        message.setTo(recipientEmail);

	        // Create the HTML body using Thymeleaf
	        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE_NAME, ctx);
	        message.setText(htmlContent, true /* isHtml */);

	        // Add the attachment
	        final InputStreamSource attachmentSource = new ByteArrayResource(attachmentBytes);
	        message.addAttachment(
	            attachmentFileName, attachmentSource, attachmentContentType);

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
	        return IOUtils.toString(inputStream, SpringMailConfig.EMAIL_TEMPLATE_ENCODING);
	    }


	    /*
	     * Send HTML mail with inline image
	     */
	    public void sendEditableMail(
	            final String recipientName, final String recipientEmail, final String htmlContent,
	            final Locale locale)
	            throws MessagingException, IOException {

	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	        final MimeMessageHelper message
	                = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
	        message.setSubject("Example editable HTML email");
	        message.setFrom("info@skinqualitycare.com.au");
	        message.setTo(recipientEmail);

	        // Prepare the evaluation context
	        final Context ctx = new Context(locale);
	        ctx.setVariable("name", recipientName);
	        ctx.setVariable("subscriptionDate", new Date());
	        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

	        // Create the HTML body using Thymeleaf
	        final String output = stringTemplateEngine.process(getEditableMailTemplate(), ctx);
	        message.setText(output, true /* isHtml */);

	        // Add the inline images, referenced from the HTML code as "cid:image-name"
	        message.addInline("product-1", new ClassPathResource(product1), PNG_MIME);
	        message.addInline("product-2", new ClassPathResource(product2), PNG_MIME);
	        message.addInline("product-3", new ClassPathResource(product3), PNG_MIME);
	        message.addInline("product-4", new ClassPathResource(product4), PNG_MIME);

	        // Send mail
	        this.mailSender.send(mimeMessage);
	    }
	    
	    
	    /*-------------------------------------------------------*/
	    /*		               Private Methods 				    */
	    /*-------------------------------------------------------*/
	    

}
