package net.endpoint.emailtemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="itype")
@Table(name = "em_template_field")
public class EmailTemplateContent {

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@Column(name="html_field_id")
	private String htmlFieldId;
	@ManyToOne
	@JoinColumn(name="em_template_id",nullable=false)
	private EmailTemplate template;

}
