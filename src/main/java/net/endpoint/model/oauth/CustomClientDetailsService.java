package net.endpoint.model.oauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class CustomClientDetailsService implements ClientDetailsService {

	
	 @Autowired
     DataSource dataSource;
	
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
