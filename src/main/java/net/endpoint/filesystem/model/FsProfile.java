package net.endpoint.filesystem.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class FsProfile {

	@Id
	private String profileId;
	private String userId;
	private String name;
	private Date timestamp;
	
	
	


	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public Date getTimestamp() {
		return timestamp;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	@Override
	public String toString() {
		return "FsProfile [profileId=" + profileId + ", userId=" + userId + ", name=" + name + ", timestamp="
				+ timestamp + "]";
	}

}
