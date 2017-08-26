package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.endpoint.emailtemplate.dto.EmailTemplateAttachmentDto;

@Entity
@DiscriminatorValue("F")
public class EmailTemplateAttachment extends EmailTemplateContent {

	@Column(name="attachment_file")
	 private byte[] attachmentDocument;
	@Column(name="file_type")
	private String fileType;

	public EmailTemplateAttachmentDto pars() {	
		return new EmailTemplateAttachmentDto(this.name,this.htmlFieldId,this.fileType,attachmentDocument);
	}
}
