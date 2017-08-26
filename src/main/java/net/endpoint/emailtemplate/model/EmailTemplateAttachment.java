package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.springframework.web.multipart.MultipartFile;

import net.endpoint.emailtemplate.dto.EmailTemplateAttachmentDto;

@Entity
@DiscriminatorValue("F")
public class EmailTemplateAttachment extends EmailTemplateContent {

	@Column(name="attachment_file")
	 private MultipartFile attachmentDocument;

	public EmailTemplateAttachmentDto pars() {	
		return new EmailTemplateAttachmentDto(this.name,this.htmlFieldId,attachmentDocument);
	}
}
