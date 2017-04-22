package net.endpoint.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import net.endpoint.model.User;

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
	public void create(User user) {
	
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
	}


	@Override
	public void update(long id) {
		
		
	}


	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
