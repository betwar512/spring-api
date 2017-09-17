package net.endpoint.filesystem.model;

import java.util.Date;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FsProfileDocument {
	@Id
	private Long id;
	private Long ownerProfile;
   // private	File file;	
	private Date timestamp;
	private String name;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOwnerProfile() {
		return ownerProfile;
	}
	public void setOwnerProfile(Long ownerProfile) {
		this.ownerProfile = ownerProfile;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "FsProfileDocument [id=" + id + ", ownerProfile=" + ownerProfile + ", timestamp=" + timestamp + ", name="
				+ name + ", description=" + description + "]";
	}
	

}
