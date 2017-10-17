package net.endpoint.institute.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DiscriminatorOptions;


@Entity
@Table(name="ins_document")
@DiscriminatorColumn(name="document_type")
@DiscriminatorOptions(force=true)
public class InsDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ins_document_id")
	private long id;

	@Column(name="file_name")
	private String fileName;
	@Column(name="file_type")
	private String fileType;
	@Column(name="file_size")
	private Long fileSize;
	@Column
	private String refrence;
	@Column(name="server_status",nullable=false)
	private String serverStatus;
	@Column(name="created_at")
	private Date  createdAt;
	@Column(name="updated_at")
	private Date  updatedAt;
	
	
	
	public long getId() {
		return id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public String getFileName() {
		return fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public String getRefrence() {
		return refrence;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setRefrence(String refrence) {
		this.refrence = refrence;
	}
	
	
	
}
