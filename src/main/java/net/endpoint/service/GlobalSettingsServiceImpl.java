package net.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.endpoint.account.dao.UserDao;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.User;

@Service
public class GlobalSettingsServiceImpl implements GlobalSettingsService {
	
	@Autowired
	UserDao userDao ;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	@Transactional(readOnly=true)
	public ProfileDto loadProfile(String email) {
		User user = userDao.findbyName(email);
			Person person = user.getPerson();
			ProfileDto p = new ProfileDto();
		    p.pars(person);
		    return p;

		}
	
	public User getUser(){
		
		return null;
	}


	@Override
	@Transactional(readOnly=true)
	public User loadUser(String email) {
		return this.userDao.findbyName(email);
		
	}
}

