package net.endpoint.config.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import net.endpoint.account.service.CustomClientDetailsService;
import net.endpoint.account.service.UserServiceImpl;
import net.endpoint.config.filter.CORSFilter;
import net.endpoint.utils.CustomEncoder;
 
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	     private static final String REALM="MY_OAUTH_REALM";
	 
	    @Value("${oath2.checktoken.url}")  
	     private String oathTokenUrl;

	    @Autowired
	     private TokenStore tokenStore;

	    @Autowired
	     DataSource dataSource;
	 
	    @Autowired
	     private UserApprovalHandler userApprovalHandler;
	 
		@Autowired
	    @Qualifier("authenticationManagerBean")
		 private AuthenticationManager authenticationManager;
	 
	    @Autowired
	    private CustomClientDetailsService clientDetailsService;
	    @Autowired
	    UserServiceImpl userDetailsService;
	    
		 @Override
		 public void configure(ClientDetailsServiceConfigurer clients) 
		      throws Exception {
		           clients.jdbc(dataSource);
//		          .withClient("client-app")
//		          .authorizedGrantTypes("password", "authorization_code", "client_credentials","refresh_token", "implicit")
//		          .authorities("ROLE_CLIENT", "ADMIN")
//		          .scopes("read", "write", "trust")
//		          .secret("testsecret")
//	              .autoApprove(true)
//	              .accessTokenValiditySeconds(600000);

		    }
	 
	    @Override
	    public void configure(
	      AuthorizationServerSecurityConfigurer oauthServer) 
	      throws Exception {
	    	oauthServer.addTokenEndpointAuthenticationFilter(new CORSFilter());
	        oauthServer.realm(REALM+"/client");
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
	          .authenticationManager(authenticationManager)
	          .userDetailsService(userDetailsService);
	    }
	 	 
	    
	    
	    @Primary
	    @Bean
	    public RemoteTokenServices tokenService() {
	        RemoteTokenServices tokenService = new RemoteTokenServices();
	        tokenService.setCheckTokenEndpointUrl(oathTokenUrl);
	        tokenService.setClientId("client-app");
	        tokenService.setClientSecret("testsecret");
	        return tokenService;
	    }
	    
	    @Bean
	    @Autowired
	    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
	        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
	        handler.setTokenStore(tokenStore);
	        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
	        handler.setClientDetailsService(clientDetailsService);
	        return handler;
	    }
	     
	    @Bean
	    @Autowired
	    public ApprovalStore approvalStore(TokenStore tokenStore) {
	        TokenApprovalStore store = new TokenApprovalStore();
	        store.setTokenStore(tokenStore);
	     
	        return store;
	    }

}
