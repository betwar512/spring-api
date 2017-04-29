package net.endpoint.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import net.endpoint.model.User;
import net.endpoint.model.account.Person;

public class ProfileDaoImpl implements ProfileDao {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public Person load(long id) {
		return sessionFactory.getCurrentSession().load(Person.class, id);
	}

	@Override
	public Person findByUser(User user) {
			@SuppressWarnings("unchecked")
			List<Person> list=	sessionFactory.getCurrentSession()
				.createQuery("from Person p where p.user = :user")
				.setParameter("user", user).list();					
		return list!=null && list.size()>0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Person findByUserName(String username) {
		 List<Person> list = sessionFactory.getCurrentSession()
				 							.createQuery("from Person p where p.user.email=:username")
				 									.setParameter("username", username).list();
		 return list!=null && list.size()>0 ? list.get(0) : null;
	}
	
	
	
	

}
