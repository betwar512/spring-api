package net.endpoint.account.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.endpoint.model.Domain;

/**
 * User base Email server class 
 * @author A.H.Safaie
 *
 */
@Entity
@Table(name="virtual_users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY) 
		private long         id;
		@Column(name="name")
		private String userName;
		@Column(name="password")
		private String password;
		@Column(name="email_password")
		private String emailPassword;
		@Column(name="email")
		private String    email;
		@Column(name="registered_email")
		private String registeredEmail;		
		@ManyToOne
		@JoinColumn(name="domain_id",nullable=false)
		private Domain   domain;
		@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
		private Person person;
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
		
		public Person getPerson() {
			return person;
		}
		public void setPerson(Person person) {
			this.person = person;
		}
		
		public String getEmailPassword() {
			return emailPassword;
		}
		public void setEmailPassword(String emailPassword) {
			this.emailPassword = emailPassword;
		}
		public String getRegisteredEmail() {
			return registeredEmail;
		}
		public void setRegisteredEmail(String registeredEmail) {
			this.registeredEmail = registeredEmail;
		}
		
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getUsername() {
			return this.getEmail();
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
	
		
		
		
}
