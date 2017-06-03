package net.endpoint.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@EnableResourceServer
public class OAuth2ResourceServerConfig  extends ResourceServerConfigurerAdapter {

	  private static final String RESOURCE_ID = "END_POINT_API";
	     
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.resourceId(RESOURCE_ID).stateless(true);
	    }
	 
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	    	
	        http
	        .anonymous()
	       // .cors()
	        .and()
	        .requestMatchers().antMatchers("/api/**")
	        .and().authorizeRequests()
	        .antMatchers("/api/**").access("permitAll()")
	        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	    }
	
	
}