package net.endpoint.emailtemplate.model;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;

@Entity
@Table(name="em_template")
public class EmailTemplate {
	
		@Id
		@Column(name="em_template_id")
		@GeneratedValue(strategy=GenerationType.IDENTITY) 
		private long id;
		@Column
		private String name;
		@Column(name="html_base_content")
		private String htmlTemplateContent;	
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "template" ,targetEntity = EmailTemplateField.class)
		private List<EmailTemplateField> fildsContents;
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "template" ,targetEntity = EmailTemplateAttachment.class)
		private List<EmailTemplateAttachment> attachments;
		@Column(name="created_at")
		private Date createdAt;
		
		
		public EmailTemplateDto pars() {
			EmailTemplateDto etd = new EmailTemplateDto(this.htmlTemplateContent,this.name,null,null);
			if(this.fildsContents != null) {
				this.fildsContents.forEach(t->etd.getFields().add(t.pars()));
			}		
			if(this.attachments != null) {
				this.attachments.forEach(t->etd.getAttachments().add(t.pars()));
			}
			return etd;
		}


		public long getId() {
			return id;
		}


		public String getName() {
			return name;
		}


		public String getHtmlTemplateContent() {
			return htmlTemplateContent;
		}


		public List<EmailTemplateField> getFildsContents() {
			return fildsContents;
		}


		public List<EmailTemplateAttachment> getAttachments() {
			return attachments;
		}


		public Date getCreatedAt() {
			return createdAt;
		}


		public void setId(long id) {
			this.id = id;
		}


		public void setName(String name) {
			this.name = name;
		}


		public void setHtmlTemplateContent(String htmlTemplateContent) {
			this.htmlTemplateContent = htmlTemplateContent;
		}


		public void setFildsContents(List<EmailTemplateField> fildsContents) {
			this.fildsContents = fildsContents;
		}


		public void setAttachments(List<EmailTemplateAttachment> attachments) {
			this.attachments = attachments;
		}


		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		
	
		
		
		
}
