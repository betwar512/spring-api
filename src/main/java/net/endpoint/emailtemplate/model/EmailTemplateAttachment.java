package net.endpoint.emailtemplate.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class EmailTemplateAttachment extends EmailTemplateContent {

	@Column(name="attachment_file")
	 private File attachmentDocument;

}
