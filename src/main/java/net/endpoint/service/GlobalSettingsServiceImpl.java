package net.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.endpoint.dao.UserDao;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.model.account.Person;

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

