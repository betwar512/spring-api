package net.endpoint.service;

import java.util.List;

import net.endpoint.model.User;

public interface UserService {

	public User get(long id);
	public User findByName(String name);
	public List<User> getAll();
}
