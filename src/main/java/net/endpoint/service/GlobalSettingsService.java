package net.endpoint.service;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;

public interface GlobalSettingsService {

	public ProfileDto loadProfile(String email);
	public User loadUser(String email);
	
}
