package net.endpoint.dao;

import java.util.List;
import net.endpoint.model.Domain;
import net.endpoint.model.User;


public interface UserDao {

	
	
	public User get(long id);
	public void delete(long id);
	public void update(User user);
	public List<User> getAll();
	public User findbyName(String name);
	public Domain getDomainByName(String domain);
	
}
