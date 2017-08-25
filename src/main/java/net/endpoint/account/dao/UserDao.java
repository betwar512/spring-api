package net.endpoint.account.dao;

import java.util.List;

import net.endpoint.account.model.User;
import net.endpoint.main.model.Domain;


public interface UserDao {

	
	
	public User get(long id);
	public void delete(long id);
	public void update(User user);
	public List<User> getAll();
	public User findbyName(String name);
	public Domain getDomainByName(String domain);
	
}
