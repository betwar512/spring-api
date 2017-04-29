package net.endpoint.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User base Email server class 
 * @author A.H.Safaie
 *
 */
@Entity
@Table(name="virtual_users")
public class User {

		@Id
		@GeneratedValue
		private long         id;
		@Column(name="name")
		private String userName;
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
		@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "user_user_security_role", joinColumns = {
		@JoinColumn(name = "user_id", nullable = false, updatable = false) },
		inverseJoinColumns = { @JoinColumn(name = "use_security_role_id",
						nullable = false, updatable = false) })
		private Set<SecurityRole> rolse = new HashSet<>(); //Many to Many 
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
		private List<Account> accounts = new ArrayList<>(); //Many-toOne
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
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
		public Set<SecurityRole> getRolse() {
			return rolse;
		}
		
		public void setRolse(Set<SecurityRole> rolse) {
			this.rolse = rolse;
		}
		
		public List<Account> getAccounts() {
			return accounts;
		}
	
		public void setAccounts(List<Account> accounts) {
			this.accounts = accounts;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", createdAt=" + createdAt
					+ ", updatedAt=" + updatedAt + "]";
		}
	
		
		
		
}
