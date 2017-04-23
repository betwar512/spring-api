package net.endpoint.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
      User user =	em.createEntityManager().createQuery(
		    "select u " +
		    "from User u " +
		    "where by u.name = :username", User.class ).setParameter("username", name).getSingleResult();
		
		
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
