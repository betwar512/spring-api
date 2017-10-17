package net.endpoint.filesystem.model;

import java.util.Date;

public class BsonCustomDocument {

	
	private String id;
	private String refrence;
	private Date timestamp;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefrence() {
		return refrence;
	}
	public void setRefrence(String refrence) {
		this.refrence = refrence;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
