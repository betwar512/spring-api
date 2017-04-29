package net.endpoint.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;


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
	public User findbyname(String name) {
//		EntityManagerFactory em = this.sessionFactory.getCurrentSession().getEntityManagerFactory();
//		CriteriaBuilder cb = this.sessionFactory.getCurrentSession().getCriteriaBuilder();
//		Metamodel m=	em.getMetamodel();
//		EntityType<User> user_ = m.entity(User.class);
//		CriteriaQuery<User> cr=em.getCriteriaBuilder().createQuery(User.class);
//	   Root<User> root=	cr.from(User.class);
//	    cr.where(cb.equal(root.get(user_.getName()), name));
	EntityManagerFactory em = this.sessionFactory.getCurrentSession().getEntityManagerFactory();
	
	
	 @SuppressWarnings("unchecked")
	List<User> users	=this.sessionFactory.getCurrentSession()
			.createQuery("from User user where user.email=:email")
			.setParameter("email", name)
			.list();
	
//     List<User> users =	em.createEntityManager().createQuery(
//		    "select u " +
//		    "from User u " +
//		    "where by u.email = 'admin@skinqualitycare.com.au'", User.class ).getResultList();
//		
		
		return users.get(0);
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
