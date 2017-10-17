package net.endpoint.institute.model.gallery;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ins_gallery_category")
public class InsGalleryCategory implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ins_gallery_category_id")
	@GeneratedValue
	private long id;
	@Column(name="name")
	private String name;
	@Column
	private String description;
	@Column(name="created_at")
	private Date timestamp;
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
