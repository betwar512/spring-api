package net.endpoint.main.service;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.User;

public interface GlobalSettingsService {

	public ProfileDto loadProfile(String email);
	public User loadUser(String email);
	
}
