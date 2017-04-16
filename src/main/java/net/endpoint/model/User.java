package net.endpoint.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	
		private long         id;
		private String     name;
		private String password;
		private String    email;
		private Domain   domain;
		private Date  createdAt;
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
