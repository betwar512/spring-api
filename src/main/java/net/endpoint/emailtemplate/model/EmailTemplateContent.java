package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="itype")
@Table(name = "em_template_field")
public class EmailTemplateContent {

	@Id
	@GeneratedValue
	protected long id;
	@Column
	protected String name;
	@Column(name="html_field_id")
	protected String htmlFieldId;
	
	
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getHtmlFieldId() {
		return htmlFieldId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHtmlFieldId(String htmlFieldId) {
		this.htmlFieldId = htmlFieldId;
	}


	
}
