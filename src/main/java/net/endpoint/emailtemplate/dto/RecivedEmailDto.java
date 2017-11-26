package net.endpoint.emailtemplate.dto;

import java.io.IOException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeMultipart;


public class RecivedEmailDto extends EmailDto {

	public enum CONTENT_TYPE{
		TEXT("text/plain"),
		MULTI("multipart/*");
		private String type;
		CONTENT_TYPE(String type){
			this.type = type;
		}
		public String getType() {
			return this.type;
		}
	}
	
	private Date recivedDate;
	private String htmlComptent;
	private CONTENT_TYPE type;
private String senderAddress;
	
	public CONTENT_TYPE getType() {
		return this.type;
	}
	
	public RecivedEmailDto(String from, String to, String subject, String content,Date recivedDate) {
		super(from, to, subject, content,"" ,null,null,null);
		this.recivedDate = recivedDate;
	}

	public RecivedEmailDto(Message message) {
		super();
		parsEmail(message);
		this.setTimestamp(new Date());
	}
	
	
	/**
	 * Pars message by type 
	 * @param message
	 */
	public void parsEmail(Message message) {
	
        try {
        	String from = message.getFrom()[0].toString();
        	this.setFrom(from.substring(0,from.indexOf("<")));
            this.senderAddress = from.substring(from.indexOf('<')+1, from.indexOf('>'));
		    this.setSubject(message.getSubject());
		    this.setRecivedDate(message.getSentDate());
		
		    if (message.isMimeType(CONTENT_TYPE.TEXT.getType())) {
		        this.setContent(message.getContent().toString());
		        this.type = CONTENT_TYPE.TEXT;
		    } else 
		    	 if (message.isMimeType(CONTENT_TYPE.MULTI.getType())) {
		    		 this.type = CONTENT_TYPE.MULTI;
		        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
		       for(int index=0;index < mimeMultipart.getCount();index++) { 
		      Part part =   mimeMultipart.getBodyPart(index);
		      if(index == 0) {
		    	  this.setContent(part.getContent().toString());
		          } else
		        	   if(index == 1) {
		        	  this.setHtmlComptent(part.getContent().toString());
		             }
		          }  		
		        }
		      } catch (MessagingException | IOException e) {
    			e.printStackTrace();
    		}
	    }


	public Date getRecivedDate() {
		return recivedDate;
	}

	public void setRecivedDate(Date recivedDate) {
		this.recivedDate = recivedDate;
	}


	public String getHtmlComptent() {
		return htmlComptent;
	}
	


	public String getSenderAddress() {
		return senderAddress;
	}

	public void setHtmlComptent(String htmlComptent) {
		this.htmlComptent = htmlComptent;
	}

	@Override
	public String toString() {
		return "RecivedEmailDto [recivedDate=" + recivedDate + ", htmlComptent=" + htmlComptent + ", type=" + type
				+ ", senderAddress=" + senderAddress + ", from=" + from + ", to=" + to + ", ccs=" + ccs + ", bccs="
				+ bccs + ", subject=" + subject + ", content=" + content + ", locale=" + locale + ", timestamp="
				+ timestamp + ", userName=" + userName + "]";
	}


	
	
	
	
	
}
