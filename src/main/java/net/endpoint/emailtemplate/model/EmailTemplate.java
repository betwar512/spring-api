package net.endpoint.emailtemplate.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="em_template")
public class EmailTemplate {
	
		@Id
		@GeneratedValue
		private long id;
		@Column
		private String name;
		@Column
		private String htmlTemplateContent;	
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "template")
		private List<EmailTemplateField> fildsContents;
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "template")
		private List<EmailTemplateAttachment> attachments;
		@Column(name="created_at")
		private Date createdAt;
}
