package net.endpoint.dao;

import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.PhoneDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.model.account.Address;
import net.endpoint.model.account.Person;
import net.endpoint.model.account.Phone;

public interface ProfileDao {

	public Person load(long id);
	public Person findByUser(User user);
	public Person findByUserName(String username);
	public void   updateProfile(ProfileDto profiledto);
	public Address addOrUpdateAddtess(AddressDto   addressdto);
	public Phone addOrUpdatePhone(PhoneDto phonedto);
}
