package net.endpoint.service;

import java.util.List;

import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.PhoneDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;

public interface UserService {

	public User       get(long id);
	public List<User> getAll();
	public User       findByName(String name);
	public ProfileDto findByUserName(String name);
	public boolean    createAccount(AccountRequestDto accountRequestDto);
	public void       updateProfile(ProfileDto profileDto);
	public boolean    createOrUpdateAddress(AddressDto address);
	public boolean    createOrUpdatePhone(PhoneDto phonedto);
	public boolean    updatePassword(User user, String password);
}
