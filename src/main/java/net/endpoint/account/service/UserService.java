package net.endpoint.account.service;

import java.util.List;

import net.endpoint.account.dto.AccountRequestDto;
import net.endpoint.account.dto.AddressDto;
import net.endpoint.account.dto.PhoneDto;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.User;

public interface UserService {

	public User       get(long id);
	public List<User> getAll();
	public User       findByName(String name);
	public ProfileDto findByUserName(String name);
	public boolean    createAccount(AccountRequestDto accountRequestDto) throws Exception;
	public void       updateProfile(ProfileDto profileDto);
	public boolean    createOrUpdateAddress(AddressDto address);
	public boolean    createOrUpdatePhone(PhoneDto phonedto);
	public boolean    updatePassword(User user, String password);
	public List<ProfileDto> getAllProfileDtos();
}
