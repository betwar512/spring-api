package net.endpoint.institute.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.endpoint.account.model.User;

@Entity
@Table(name="ins_practitioner")
public class InsPractitioner  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ins_practitioner_id")
	private long id;
	@OneToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	@Column(name="iactive")
	private boolean active;
	@Column(name="created_at")
	private Date timestamp;
	

	
	public long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}

	public boolean isActive() {
		return active;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	
}
