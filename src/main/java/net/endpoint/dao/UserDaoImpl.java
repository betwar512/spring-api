package net.endpoint.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;


import org.hibernate.SessionFactory;

import com.mysql.cj.core.util.StringUtils;

import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.model.Domain;
import net.endpoint.model.User;
import net.endpoint.util.CustomEncoder;
import net.endpoint.util.CustomTypes.DOMAINS;

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
		return users.get(0);
	}
	

	/**
	 * <p>Create a new user </p>
	 * @return boolean ? false == not valid 
	 */
	@Override
	public boolean create(AccountRequestDto accountRequestDto) {
	
	  if(accountRequestDto!=null){
			User user = new User();
			user.setUserName(accountRequestDto.userName);
			Domain domain = this.getDomainByName(accountRequestDto.domainName);		
		 if(domain!=null){
			user.setDomain(domain);
			boolean passwordIsValid = !StringUtils.isNullOrEmpty(accountRequestDto.password) &&
					                   accountRequestDto.password.equals(accountRequestDto.rePassword) ;
		  if(passwordIsValid){
			CustomEncoder   cr = new CustomEncoder();
			String encodedPass = cr.encode(accountRequestDto.password);
			user.setPassword(encodedPass);
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			this.saveDb(user);
			return true;
			  }//password
		   }//domain 
		}//dto
		return false;
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
	
	  /*-----------------------------------------------------------*/
	 /*			Private 										  */
	/*-----------------------------------------------------------*/
	
	/**
	 * Find domain by name 
	 * @param domain
	 * @return Domain 
	 */
	private Domain getDomainByName(String domain){
		if(domain!=null && !domain.isEmpty()){
		return (Domain) this.sessionFactory.getCurrentSession()
					.createQuery("from Domain domain where domain.name=:name")
						.setParameter("name", domain)
						   .uniqueResult();
		    }
	 return null ;
	}
	
	private void saveDb(Object o){
		this.sessionFactory.getCurrentSession().saveOrUpdate(o);
	}
	
}
