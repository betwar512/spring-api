package net.endpoint.dao;

import java.util.List;

import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.model.User;

public interface UserDao {

	
	public List<User> getAll();
	public User get(long id);
	public boolean create(AccountRequestDto accountRequestDto);
	public void delete(long id);
	User findbyName(String name);
	public User changePassword(User user,String password);
	
}
