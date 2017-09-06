package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.endpoint.emailtemplate.dto.EmailTemplateAttachmentDto;

@Entity
@DiscriminatorValue("F")
public class EmailTemplateAttachment extends EmailTemplateContent {

	@Column(name="attachment_file")
	 private byte[] attachmentDocument;
	@Column(name="file_type")
	private String fileType;
	@ManyToOne
	@JoinColumn(name="em_template_id",nullable=false)
	protected EmailTemplate template;
	
	public EmailTemplateAttachmentDto pars() {	
		return new EmailTemplateAttachmentDto(this.name,this.htmlFieldId,this.fileType,attachmentDocument);
	}
}
