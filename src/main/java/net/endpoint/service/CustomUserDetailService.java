package net.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import net.endpoint.dao.UserDao;
import net.endpoint.model.User;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserDao userDao;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findbyname(username);
		
		return null; //userDao.findbyname(username);
	}

}
