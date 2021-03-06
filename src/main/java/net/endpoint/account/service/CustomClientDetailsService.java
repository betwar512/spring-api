package net.endpoint.account.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import net.endpoint.main.model.ClientDetailsImpl;

@Service
public class CustomClientDetailsService  implements ClientDetailsService  {

	
	 @Autowired
     SessionFactory sessionFactory;
	
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {	
	 return sessionFactory.getCurrentSession().load(ClientDetailsImpl.class,clientId);
	}

}
