package net.endpoint.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="virtual_users")
public class User {

		@Id
		@GeneratedValue
		private long         id;
		private String     name;
		private String password;
		private String    email;
		@ManyToOne
		@JoinColumn(name="domain_id",nullable=false)
		private Domain   domain;
		@Column(name="created_at")
		@Type(type = "date")
		private Date  createdAt;
		@Column(name="updated_at")
		@Type(type = "date")
		private Date  updatedAt;
		
		
		
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		@JsonIgnore
		public Domain getDomain() {
			return domain;
		}
		public void setDomain(Domain domain) {
			this.domain = domain;
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
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		
		

	
}
