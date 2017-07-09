package net.endpoint.dao;

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
	public void update(Person person);
	public boolean updateProfile(ProfileDto profiledto);
	public void updatePhone(Phone phone);
	public Address loadAddress(long id);
	public Phone loadPhone(long id);
	public void save(Object o);
	public void updateAddress(Address address);
}
