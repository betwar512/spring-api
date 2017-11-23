package net.endpoint.config.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableAuthorizationServer
public class OAuth2ResourceServerConfig  extends ResourceServerConfigurerAdapter {

	 @Value("$oath2.resource_id") 
	 private String resourceId;
	     
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.resourceId(resourceId).stateless(true);
	    }
	 
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	    	
	        http
	        .anonymous()
	       // .and().cors(CrossOriginFilter.class)
	        .and().requestMatchers().antMatchers("/api/**")
	        .and().authorizeRequests().antMatchers("/api/**").authenticated()
	        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	    }
	
	 
	
}
