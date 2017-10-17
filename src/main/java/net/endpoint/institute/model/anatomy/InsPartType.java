package net.endpoint.institute.model.anatomy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ins_part_type")
public class InsPartType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ins_part_type_id")
	private long id;
	@Column(name="name_value")
	private String name;
	@Column(name="formated_value")
	private String formatedName;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updated_at")
	private Date updatedAt;
	
	
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getFormatedName() {
		return formatedName;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFormatedName(String formatedName) {
		this.formatedName = formatedName;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	

}
