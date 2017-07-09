package net.endpoint.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import net.endpoint.model.Domain;

public class DomainDaoImpl implements DomainDao {

	
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	public Domain get(long id) {
	   return this.sessionFactory.getCurrentSession().load(Domain.class, id);
	}


	public Domain findByName(String name) {
	@SuppressWarnings("unchecked")
	List<Domain> domains = this.sessionFactory.getCurrentSession()
				.createQuery("from Domain domain where domain.name=:name")
				.setParameter("name", name)
				.list();
		return domains.isEmpty() ? null : domains.get(0);
	}

	
	public Long saveOrUpdate(Domain domain) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain.getId();
	}

	
	
	
	public void delete(long id) {
		
		Domain domain = this.get(id);
		     domain.setActive(false);
		   this.saveOrUpdate(domain);
	}



}
