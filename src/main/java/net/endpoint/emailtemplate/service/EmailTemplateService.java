package net.endpoint.emailtemplate.service;

import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;

public interface EmailTemplateService {

  public void	sendTextMail(
	        final String recipientName, final String recipientEmail, final Locale locale)
	        throws MessagingException;
	
  public void sendSimpleMail(
	        final String recipientName, final String recipientEmail, final Locale locale)
	        throws MessagingException;
  /* 
   * Send HTML mail with attachment. 
   */
  public void sendMailWithAttachment(
      final String recipientName, final String recipientEmail, final String attachmentFileName,
      final byte[] attachmentBytes, final String attachmentContentType, final Locale locale)
      throws MessagingException;
  
  
  /* 
   * Send HTML mail with in-line image
   */
  public void sendMailWithInline(
      final String recipientName, final String recipientEmail, final String imageResourceName,
      final byte[] imageBytes, final String imageContentType, final Locale locale)
      throws MessagingException ;
  /*
   * Send HTML mail with inline image
   */
  public void sendEditableMail(
          final String recipientName, final String recipientEmail, final String htmlContent,
          final Locale locale)
          throws MessagingException, IOException ;
}
