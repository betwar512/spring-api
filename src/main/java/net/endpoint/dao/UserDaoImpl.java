package net.endpoint.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;


import org.hibernate.SessionFactory;

import com.mysql.cj.core.util.StringUtils;

import net.endpoint.model.User;
import net.endpoint.util.CustomEncoder;

/**
 * 
 * @author Betwar
 *
 */
public class UserDaoImpl implements UserDao {

	
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public List<User> getAll() {
		@SuppressWarnings("unchecked")
		List<User>	result=this.sessionFactory.getCurrentSession().createQuery("from User user").list();
		return result;
	}
	

	@Override
	public User get(long id) {
		User user = this.sessionFactory.getCurrentSession().get(User.class,id);
		return user;
	}

	@Override
	public User findbyname(String name) {
	 @SuppressWarnings("unchecked")
	List<User> users	= this.sessionFactory.getCurrentSession()
			.createQuery("from User user where user.email=:email")
			.setParameter("email", name)
			.list();
		return users.get(0);
	}
	
	

	@Override
	public void create(User user) {
	
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User changePassword(User user,String password) {
		if(!StringUtils.isNullOrEmpty(password)){
			CustomEncoder encoder = new CustomEncoder();
			String hash=encoder.encode(password);
			user.setPassword(hash);
		    this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		}
		
		return user;
	}
	
	
}
