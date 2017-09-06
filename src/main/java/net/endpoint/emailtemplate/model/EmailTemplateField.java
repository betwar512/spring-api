package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.endpoint.emailtemplate.dto.EmailTemplateFieldDto;

@Entity
@DiscriminatorValue("C")
public class EmailTemplateField  extends EmailTemplateContent {
	
	@Column(name="field_content")
	private String fieldContent;
	@ManyToOne
	@JoinColumn(name="em_template_id",nullable=false)
	protected EmailTemplate template;

	public EmailTemplateFieldDto pars() {
	return  new EmailTemplateFieldDto(this.name,this.htmlFieldId,this.fieldContent);
	}

}
