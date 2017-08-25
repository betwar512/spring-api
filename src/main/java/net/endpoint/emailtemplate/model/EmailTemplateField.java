package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class EmailTemplateField  extends EmailTemplateContent {
	
	@Column(name="field_content")
	private String fieldContent;
	

}
