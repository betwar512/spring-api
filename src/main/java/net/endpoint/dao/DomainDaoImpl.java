package net.endpoint.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import net.endpoint.model.Domain;

@Repository
public class DomainDaoImpl extends BaseDao implements DomainDao {


	public Domain get(long id) {
	   return this.getSession().load(Domain.class, id);
	}


	public Domain findByName(String name) {
	@SuppressWarnings("unchecked")
	List<Domain> domains = this.getSession()
				.createQuery("from Domain domain where domain.name=:name")
				.setParameter("name", name)
				.list();
		return domains.isEmpty() ? null : domains.get(0);
	}

	
	public Long saveOrUpdate(Domain domain) {
		this.getSession().saveOrUpdate(domain);
		return domain.getId();
	}

	
	
	
	public void delete(long id) {
		
		Domain domain = this.get(id);
		     domain.setActive(false);
		   this.saveOrUpdate(domain);
	}



}
