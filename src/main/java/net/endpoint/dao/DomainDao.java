package net.endpoint.dao;

import net.endpoint.model.Domain;

public interface DomainDao {
	
	public Domain get(long id);
	public Domain findByName(String name);
	public Long   saveOrUpdate(Domain domain);
	public void   delete(long id);
  
}
