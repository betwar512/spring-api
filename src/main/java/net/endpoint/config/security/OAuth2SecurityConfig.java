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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import net.endpoint.config.filter.CORSFilter;
import net.endpoint.utils.CustomEncoder;

/**
 * 
 * @author A.H.Safaie 
 *
 */
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String USER_QUERY = "select email as username,password,iactive as enabled from virtual_users where email=?";
	private static final String USER_ROLE_QUERY = 
			     "select u.email as username,rl.name as role FROM virtual_users u,user_security_role rl "
		   		+ " JOIN user_user_security_role user_role "
		   		+ " where user_role.user_id = u.id AND u.email=?";
	
		@Autowired
		DataSource dataSource;

	   /**
	    * <p>User Oath taking place here ,Using custom query to handle Authentication for grandt_type : password </p>
	    * @param auth
	    * @throws Exception
	    */
	   @Autowired
	   protected void globalUserDetails(AuthenticationManagerBuilder auth)  throws Exception {

		   auth.jdbcAuthentication()
		       .dataSource(dataSource)
		       .passwordEncoder(new CustomEncoder())
			   .usersByUsernameQuery(USER_QUERY)
			   .authoritiesByUsernameQuery(USER_ROLE_QUERY);
	    }
	  
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	   http
	           .csrf().disable()
	           .cors().and()
	          // .anonymous().disable()
	           .authorizeRequests()
	           .antMatchers(HttpMethod.OPTIONS,"/oauth/token").permitAll()
	           .antMatchers("/resources/**").permitAll()
	           .anyRequest().authenticated();
	    }
	    
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() 
	      throws Exception {
	        return super.authenticationManagerBean();
	    }
	    
	    @Bean
	    public TokenStore tokenStore() {
	        return new JdbcTokenStore(dataSource);
	    }
	    
}