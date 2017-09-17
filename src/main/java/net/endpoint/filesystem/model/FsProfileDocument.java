package net.endpoint.filesystem.model;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FsProfileDocument {
	
	@Id
	private String id;
	private String ownerProfile;   
	private Date timestamp;
	private String name;
	private String description;
	private byte[] file;
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerProfile() {
		return ownerProfile;
	}

	public void setOwnerProfile(String ownerProfile) {
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}



	@Override
	public String toString() {
		return "FsProfileDocument [id=" + id + ", ownerProfile=" + ownerProfile + ", timestamp=" + timestamp + ", name="
				+ name + ", description=" + description + ", file=" + Arrays.toString(file) + "]";
	}


	

}
