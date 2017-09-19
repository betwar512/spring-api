package net.endpoint.institute.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.endpoint.institute.model.anatomy.InsBodyPart;


@Entity
@Table(name="ins_document")
public class InsDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ins_document_id")
	private long id;
	@ManyToOne
	@JoinColumn(name="ins_body_part_id")
	private InsBodyPart part;
	@Column(name="created_at")
	private Date  createdAt;
	@Column(name="updated_at")
	private Date  updatedAt;
	@Column(name="file_name")
	private String fileName;
	@Column(name="file_type")
	private String fileType;
	@Column
	private String refrence;
	
	
	
	public long getId() {
		return id;
	}
	public InsBodyPart getPart() {
		return part;
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
	public void setPart(InsBodyPart part) {
		this.part = part;
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
