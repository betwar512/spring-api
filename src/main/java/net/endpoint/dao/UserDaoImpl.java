package net.endpoint.dao;

import java.util.List;

import org.hibernate.SessionFactory;


import net.endpoint.model.User;

public class UserDaoImpl implements UserDao {

	
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public List<User> get() {
		
		return list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list(){	
	List<User>	result=this.sessionFactory.getCurrentSession().createQuery("from User user").list();
	
	return result;
	}
	
	
}
