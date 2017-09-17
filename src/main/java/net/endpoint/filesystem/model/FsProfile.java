package net.endpoint.filesystem.model;

import java.util.Date;



public class FsProfile {

	private String profileId;
	private String userId;
	private String name;
	private Date timestamp;
	
	
	
	public String getProfileId() {
		return profileId;
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
	public void setProfileId(String profileId) {
		this.profileId = profileId;
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
