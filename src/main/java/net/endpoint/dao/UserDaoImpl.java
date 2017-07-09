package net.endpoint.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import net.endpoint.model.Domain;
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
	@SuppressWarnings("unchecked")
	public List<User> getAll() {		
		return this.sessionFactory
							.getCurrentSession()
							  .createQuery("from User user")
							       .list();
		
	}
	

	@Override
	public User get(long id) {
		return this.sessionFactory
				          .getCurrentSession()
				                     .get(User.class,id);
	}

	@Override
	public User findbyName(String name) {
	 @SuppressWarnings("unchecked")
	List<User> users	= this.sessionFactory.getCurrentSession()
			.createQuery("from User user where user.email=:email")
			.setParameter("email", name)
			.list();
		return users.isEmpty() ? null : users.get(0);
	}
	

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(User user){
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	
	  /*-----------------------------------------------------------*/
	 /*			Private 										  */
	/*-----------------------------------------------------------*/
	
	/**
	 * Find domain by name 
	 * @param domain
	 * @return Domain 
	 */
	public Domain getDomainByName(String domain){
		if(domain!=null && !domain.isEmpty()){
		return (Domain) this.sessionFactory.getCurrentSession()
					.createQuery("from Domain domain where domain.name=:name")
						.setParameter("name", domain)
						   .uniqueResult();
		    }
	 return null ;
	}



	

	
}
