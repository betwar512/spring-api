package net.endpoint.config.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;



import net.endpoint.util.CustomEncoder;

/**
 * 
 * @author A.H.Safaie 
 *
 */
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		DataSource dataSource;

	   @Autowired
	    private ClientDetailsService clientDetailsService;
	
	   /**
	    * <p>User Oauth taking place here ,Using custom query to handle Authentication for grandt_type : password </p>
	    * @param auth
	    * @throws Exception
	    */
	   @Autowired
	   protected void globalUserDetails(AuthenticationManagerBuilder auth)  throws Exception {
		   String userQuery  = "select email as username,password,domain_id as enabled from virtual_users where email=?";
		//   String userByRule = "select user_name as username,role from user_role where user_name=?";
		   String userByRule = "select u.email as username,rl.name as role FROM virtual_users u,user_security_role rl "
		   		+ " JOIN user_user_security_role user_role "
		   		+ " where user_role.user_id = u.id AND u.email=?";
		   auth.jdbcAuthentication()
		       .passwordEncoder(new CustomEncoder()) //Use custom encoder to read Dovcote encoded pass 
		       .dataSource(dataSource)
			   .usersByUsernameQuery(userQuery)
			   .authoritiesByUsernameQuery(userByRule);
	    }
	  
	
	  
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	   http
	        //   .csrf().disable()
	          // .anonymous().disable()
	           .authorizeRequests()
	           .antMatchers(HttpMethod.OPTIONS,"/oauth/token").permitAll()
	           .antMatchers("/resources/**").permitAll()
	           .anyRequest().authenticated();
	    }
     
	  
	    @Bean
	    public TokenStore tokenStore() {
	        return new JdbcTokenStore(this.dataSource)
	        		;//new InMemoryTokenStore();
	    }

	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() 
	      throws Exception {
	        return super.authenticationManagerBean();
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
	    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
	    	System.out.println("Hit in token check ");
	        TokenApprovalStore store = new TokenApprovalStore();
	        store.setTokenStore(tokenStore);
	     
	        return store;
	    }
	    
	    
}