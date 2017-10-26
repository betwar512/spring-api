package net.endpoint.config.security;

import org.springframework.security.core.Authentication;
/**
 * Globle access to Authentication object 
 * @author Betwar
 *
 */
public interface IAuthenticationFacade {

	public Authentication getAuthentication();
}
