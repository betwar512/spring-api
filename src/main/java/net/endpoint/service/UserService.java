package net.endpoint.service;

import java.util.List;

import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.PhoneDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;

public interface UserService {

	public User       get(long id);
	public User       findByName(String name);
	public List<User> getAll();
	public ProfileDto findByUserName(String name);
	public void       updateProfile(ProfileDto profileDto);
	public AddressDto createOrUpdateAddress(AddressDto address);
	public PhoneDto   createOrUpdatePhone(PhoneDto phonedto);
	public User       updatePassword(User user, String password);
}
