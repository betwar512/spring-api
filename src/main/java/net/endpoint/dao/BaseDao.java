package net.endpoint.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.endpoint.model.SecurityRole;
import net.endpoint.util.CustomTypes.SECURITY_ROLE;

@Repository
public abstract class BaseDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public SecurityRole loadSecurityRole(SECURITY_ROLE type){
	List<SecurityRole> list = this.getSession()
		.createCriteria(SecurityRole.class)
		.add(Restrictions.eq("name",type.name()))
		.list();
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}
	
	
}
