package net.endpoint.filesystem.model;

import java.io.File;
import java.util.Date;

import javax.persistence.Id;


public class FsProfileDocument {
	@Id
	private String id;
	private String ownerProfile;
    private	File file;	
	private Date timestamp;
	private String name;
	private String description;
	
	
	
	public String getId() {
		return id;
	}
	public File getFile() {
		return file;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwnerProfile() {
		return ownerProfile;
	}
	public void setOwnerProfile(String ownerProfile) {
		this.ownerProfile = ownerProfile;
	}
	
	@Override
	public String toString() {
		return "FsProfileDocument [id=" + id + ", ownerProfile=" + ownerProfile + ", file=" + file + ", timestamp="
				+ timestamp + ", name=" + name + ", description=" + description + "]";
	}

}
