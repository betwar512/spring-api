package net.endpoint.model.oauth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Entity
@Table(name="ClientDetails")
public class ClientDetailsImpl implements ClientDetails {

	private static final long serialVersionUID = 5101520299011663465L;
	//	 appId VARCHAR(255) PRIMARY KEY,
//	  resourceIds VARCHAR(255),
//	  appSecret VARCHAR(255),
//	  scope VARCHAR(255),
//	  grantTypes VARCHAR(255),
//	  redirectUrl VARCHAR(255),
//	  authorities VARCHAR(255),
//	  access_token_validity INTEGER,
//	  refresh_token_validity INTEGER,
//	  additionalInformation VARCHAR(4096),
//	  autoApproveScopes VARCHAR(255)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private String appId;
	@Column
	private String resourceIds;
	@Column
	private String appSecret;
	@Column
	private String scope;
	@Column
	private String grantTypes;
	@Column
	private String redirectUrl;
	@Column
	private String authorities;
	@Column(name="access_token_validity")
	private Integer validAccessToken;
	@Column(name="refresh_token_validity")
	private Integer validRefreshToken;
	@Column
	private String additionalInformation;
	@Column
	private String autoApproveScopes;
	
	@Override
	public String getClientId() {
		return this.appId;
	}

	@Override
	public Set<String> getResourceIds() {
	   Set<String> mySet = new HashSet<>();		   
	   for(String str: this.resourceIds.split(",")){
		   mySet.add(str);
		   }
		return mySet;
	}

	@Override
	public boolean isSecretRequired() {
		return false;
	}

	@Override
	public String getClientSecret() {
		return this.appSecret;
	}

	@Override
	public boolean isScoped() {
		return false;
	}

	@Override
	public Set<String> getScope() {
		return null;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return null;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		  Set<String> set = new HashSet<>();		   
		   for(String str: this.redirectUrl.split(",")){
			   set.add(str);
			   }
		return set;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
	
		return null;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return null;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return null;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}

}
