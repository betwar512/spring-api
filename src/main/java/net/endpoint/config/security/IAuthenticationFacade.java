package net.endpoint.config.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

	public Authentication getAuthentication();
}
