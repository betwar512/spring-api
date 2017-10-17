package net.endpoint.main.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="virtual_domain")
public class Domain implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@Column(name="ivalid" , nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean active;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
