package net.endpoint.emailtemplate.model;


import java.util.ArrayList;
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
		@Column(name="description")
		private String description;
		@Column(name="html_base_content")
		private String htmlTemplateContent;	
		@Column(name="html_header")
		private String header;
		@Column(name="css_Style")
		private String style;
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "template" ,targetEntity = EmailTemplateField.class)
		private List<EmailTemplateField> fildsContents  = new ArrayList<>();
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "template" ,targetEntity = EmailTemplateAttachment.class)
		private List<EmailTemplateAttachment> attachments = new ArrayList<>();
		@Column(name="created_at")
		private Date createdAt;
		
		/**
		 * Pars model to dto object 
		 * @return
		 */
		public EmailTemplateDto pars() {
			EmailTemplateDto etd = new EmailTemplateDto(this.htmlTemplateContent,this.name,this.description,this.style,null,null);
			if(this.fildsContents != null) {
				this.fildsContents.forEach(t->etd.getFields().add(t.pars()));
			}		
			if(this.attachments != null) {
				this.attachments.forEach(t->etd.getAttachments().add(t.pars()));
			}
			return etd;
		}

		/**
		 * Write dto into model object 
		 * @param emailTemplateDto
		 */
		public void formate(EmailTemplateDto emailTemplateDto) {
			this.name = emailTemplateDto.getName();
			this.description = emailTemplateDto.getDescription();
			this.style = emailTemplateDto.getStyle();
			this.htmlTemplateContent = emailTemplateDto.getHtmlContent();
			this.createdAt = new Date();
			
		}
		
		/**
		 * Pars without relations 
		 * @return
		 */
		public EmailTemplateDto parsGeneral() {
			return new EmailTemplateDto(this.htmlTemplateContent,this.name,this.description,this.style,null,null);
		}

		public String buildContent() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n" + 
					"<head>\r\n" + 
					"  <META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=us-ascii\">\r\n" + 
					"  <meta name=Generator content=\"Microsoft Word 15 (filtered medium)\">");
			stringBuilder.append(this.style);
			stringBuilder.append("</head>");
			
			stringBuilder.append(this.htmlTemplateContent);
			stringBuilder.append("</html>");
			
			return stringBuilder.toString();
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


		public String getHeader() {
			return header;
		}


		public void setHeader(String header) {
			this.header = header;
		}


		public String getStyle() {
			return style;
		}


		public void setStyle(String style) {
			this.style = style;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}
		
		
	
		
		
		
}
