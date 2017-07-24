package net.endpoint.config.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import net.endpoint.config.filter.CORSFilter;
import net.endpoint.util.CustomEncoder;
 
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	 private static final String REALM="MY_OAUTH_REALM";
	 private static final String URL_DEV =  "http://localhost:8080/oauth/check_token";
	    @Autowired
	     private TokenStore tokenStore;

	     @Autowired
	     DataSource dataSource;
	 
	     @Autowired
	     private UserApprovalHandler userApprovalHandler;
	 
		 @Autowired
		 @Qualifier("authenticationManagerBean")
		 private AuthenticationManager authenticationManager;
	 
		 @Override
		 public void configure(ClientDetailsServiceConfigurer clients) 
		      throws Exception {
		           clients.jdbc(dataSource)
		          .withClient("client-app")
		          .authorizedGrantTypes("password", "authorization_code", "client_credentials","refresh_token", "implicit")
		          .authorities("ROLE_CLIENT", "ADMIN")
		          .scopes("read", "write", "trust")
		          .secret("testsecret")
	              .autoApprove(true)
	              .accessTokenValiditySeconds(60000000);

		    }
	 
	    @Override
	    public void configure(
	      AuthorizationServerSecurityConfigurer oauthServer) 
	      throws Exception {
	    	oauthServer.addTokenEndpointAuthenticationFilter(new CORSFilter());
	        oauthServer.realm(REALM+"/client");//allowFormAuthenticationForClients();
	        oauthServer.checkTokenAccess("permitAll()");
	    }
	     
	    @Bean
		public PasswordEncoder passwordEncoder(){
			return new CustomEncoder();
		}
	       
	    @Override
	    public void configure(
	      AuthorizationServerEndpointsConfigurer endpoints) 
	      throws Exception {
	    		
	        endpoints
	          .tokenStore(tokenStore)
	          .userApprovalHandler(userApprovalHandler)
	          .authenticationManager(authenticationManager);
	    }
	 	 
	    
	    
	    @Primary
	    @Bean
	    public RemoteTokenServices tokenService() {
	        RemoteTokenServices tokenService = new RemoteTokenServices();
	        tokenService.setCheckTokenEndpointUrl(URL_DEV);
	        tokenService.setClientId("client-app");
	        tokenService.setClientSecret("testsecret");
	        return tokenService;
	    }
	    
}
