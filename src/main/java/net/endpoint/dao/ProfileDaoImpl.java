package net.endpoint.dao;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.model.account.Address;
import net.endpoint.model.account.Person;
import net.endpoint.model.account.Phone;

public class ProfileDaoImpl implements ProfileDao {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public Person load(long id) {
		return sessionFactory.getCurrentSession().load(Person.class, id);
	}

	@Override
	public Person findByUser(User user) {
			@SuppressWarnings("unchecked")
			List<Person> list=	sessionFactory.getCurrentSession()
				.createQuery("from Person p where p.user = :user")
				.setParameter("user", user).list();					
		return list!=null && list.size()>0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Person findByUserName(String username) {
		 List<Person> list = sessionFactory.getCurrentSession()
				 						   .createQuery("from Person p where p.user.email=:username")
				 						   .setParameter("username", username).list();
		 return list!=null && list.size()>0 ? list.get(0) : null;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
   private User getUser(String email){
		UserDaoImpl dao =   new UserDaoImpl();		
		dao.setSessionFactory(this.sessionFactory);
	return dao.findbyName(email);
  }
	
	

	/**
	 *<p> Update profile from Profile Dto </p>
	 *@return boolean 
	 */
	@Transactional
	@Override
	public boolean updateProfile(ProfileDto profiledto) {	
		boolean result = false;
	 if(profiledto != null){
	    Person person = findByUserName(profiledto.getEmail());
		   if(person == null) {
			   person  = new Person();
				person.setCreatedAt(new Date());
				User user = getUser(profiledto.getEmail());
				person.setUser(user);
				this.sessionFactory.getCurrentSession().save(person);
		       }
			if(!profiledto.getFirstname().isEmpty()){
			    person.setFirstName(profiledto.getFirstname());
			    }
			if(profiledto.getLastname() != null){
				person.setLastName(profiledto.getLastname());
				}
			if(profiledto.getDob() != null){
				   	person.setDateOfBirth(profiledto.getDob());
				   	}
			
			 person.setUpdatedAt(new Date());
		   save(person);
		return true;
		 
	  }
	 return result;
	}
	
	@Override
	public Address loadAddress(long id){
		return this.sessionFactory.getCurrentSession().load(Address.class, id);
	}
	@Override
	public Phone loadPhone(long id){
		return this.sessionFactory.getCurrentSession().load(Phone.class, id);
	}
	@Override
	public void save(Object o){
		sessionFactory.getCurrentSession().saveOrUpdate(o);
	}


/**
 * 
 */
	@Override
	public void updateAddress(Address address) {
		if(address!=null){
			this.save(address);
		}
		
	}


	/**
	 * <p>Save Or update phone</p>
	 * @return Phone  
	 * 
	 */
	@Override
	public void updatePhone(Phone phone) {
		if(phone!=null){
			this.save(phone);
		}
	}


	@Override
	public void update(Person person) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(person);
		
	}
	
	

}
