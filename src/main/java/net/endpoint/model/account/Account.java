package net.endpoint.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.endpoint.model.User;

@Entity
@Table(name="user_account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="user_account_id")
	@GeneratedValue
	private long         id;
	@Column(name="iactive")
	private boolean  active;
	@Column(name="user_name")
	private String userName;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User       user;
	@Column(name="created_at")
	private Date  createdAt;
	@Column(name="updated_at")
	private Date  updatedAt;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", active=" + active + ", userName=" + userName + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
	
	
}
