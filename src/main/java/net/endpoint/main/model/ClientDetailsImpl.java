package net.endpoint.main.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Entity
@Table(name="oauth_client_details")
public class ClientDetailsImpl implements ClientDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="client_id")
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
	@Column(name="authorized_grant_types")
	private String authorities;
	@Column(name="access_token_validity")
	private Integer validAccessToken;
	@Column(name="refresh_token_validity")
	private Integer validRefreshToken;
	@Column(name="additional_information")
	private String additionalInformation;
	@Column(name="autoapprove")
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
		return !this.appSecret.isEmpty();
	}

	@Override
	public String getClientSecret() {
		return this.appSecret;
	}

	@Override
	public boolean isScoped() {
		return !this.scope.isEmpty();
	}

	@Override
	public Set<String> getScope() {
		return this.toSet(this.scope);
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return this.toSet(this.authorities);
	}

	private Set<String> toSet(String arg){
		String[] strs = arg.split(",");
		Set<String> list = new HashSet<>();
		for(String str:strs){
			list.add(str);
		}
		return list;
	}
	
	@Override
	public Set<String> getRegisteredRedirectUri() {
		return this.toSet(this.redirectUrl);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
	String[] strs =	authorities.split(",");
	return GrantedAuthorityImpl.getAuthoritize(strs);
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return this.validAccessToken;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return this.validRefreshToken;
	}

	@Override
	public boolean isAutoApprove(String scope) {
	Set<String> strs =  this.toSet(this.autoApproveScopes);
		return strs.contains(scope);
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}

}
