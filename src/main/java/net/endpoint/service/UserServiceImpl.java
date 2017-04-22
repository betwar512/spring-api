package net.endpoint.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import net.endpoint.dao.UserDao;
import net.endpoint.model.User;

public class UserServiceImpl implements UserService {

	
	UserDao userDao;
	TransactionTemplate transactionTemplate;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
		}


	@Transactional(readOnly=true)
	public List<User> getAll(){
		List<User> result=this.userDao.getAll();
		return result;
	}


	@Transactional(readOnly=true)
	public User get(long id) {
		return userDao.get(id);
	}
	
	
}
