package net.endpoint.dao;

import net.endpoint.model.User;
import net.endpoint.model.account.Person;

public interface ProfileDao {

	public Person load(long id);
	public Person findByUser(User user);
	public Person findByUserName(String username);
	
	
}
