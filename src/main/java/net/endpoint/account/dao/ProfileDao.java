package net.endpoint.account.dao;

import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Address;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.Phone;
import net.endpoint.account.model.User;

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
