package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.endpoint.emailtemplate.dto.EmailTemplateFieldDto;

@Entity
@DiscriminatorValue("C")
public class EmailTemplateField  extends EmailTemplateContent {
	
	@Column(name="field_content")
	private String fieldContent;
	
	public EmailTemplateFieldDto pars() {
	return  new EmailTemplateFieldDto(this.name,this.htmlFieldId,this.fieldContent);
	}

}
