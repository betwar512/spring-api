package net.endpoint.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
 
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	 private static String REALM="MY_OAUTH_REALM";

	     @Autowired
	      private TokenStore tokenStore;

	     @Autowired
	     private UserApprovalHandler userApprovalHandler;
	 
		  @Autowired
		  @Qualifier("authenticationManagerBean")
		  private AuthenticationManager authenticationManager;
		 
	 
	    @Override
	    public void configure(
	      AuthorizationServerSecurityConfigurer oauthServer) 
	      throws Exception {
	        oauthServer.allowFormAuthenticationForClients();//.realm(REALM+"/client");
	    }
	 
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) 
	      throws Exception {
	        clients.inMemory()//jdbc(dataSource())
	          .withClient("client-app")
	          .authorizedGrantTypes("password", "authorization_code", "client_credentials","refresh_token", "implicit")
	          .authorities("ROLE_CLIENT", "ADMIN")
	          .scopes("read", "write", "trust")
              .autoApprove(true);
//	          .and()
//	          .withClient("client-app")
//	          .secret("secret")
//	          .authorizedGrantTypes(
//	            "password","authorization_code", "refresh_token")
//	          .scopes("read", "write", "trust");
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
	 
//	    @Bean
//	    public TokenStore tokenStore() {
//	        return new JdbcTokenStore(dataSource());
//	    }
	 
	    @Value("classpath:schema.sql")
	    private Resource schemaScript;
	     
	    @Bean
	    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
	        DataSourceInitializer initializer = new DataSourceInitializer();
	        initializer.setDataSource(dataSource);
	        initializer.setDatabasePopulator(databasePopulator());
	        return initializer;
	    }
	     
	    private DatabasePopulator databasePopulator() {
	        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	        populator.addScript(schemaScript);
	        return populator;
	    }
	     
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://52.63.208.154:3306/test");
	        dataSource.setUsername("root");
	        dataSource.setPassword("Solmaz662M");
	        return dataSource;
	    }
	    
//	    @Primary
//	    @Bean
//	    public RemoteTokenServices tokenService() {
//	        RemoteTokenServices tokenService = new RemoteTokenServices();
//	        tokenService.setCheckTokenEndpointUrl(
//	          "http://localhost:8080/oauth/check_token");
//	        tokenService.setClientId("client-app");
//	        tokenService.setClientSecret("secret");
//	        return tokenService;
//	    }   
}