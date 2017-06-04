package net.endpoint.service;
import net.endpoint.dto.account.ProfileDto;

public interface GlobalSettingsService {

	public ProfileDto loadProfile(String email);
}
