package net.endpoint.dao;

import java.util.List;

import net.endpoint.model.User;

public interface UserDao {

	
	public List<User> getAll();
	public User get(long id);
	public void create(User user);
	public void update(long id);
	public void delete(long id);
	
}
