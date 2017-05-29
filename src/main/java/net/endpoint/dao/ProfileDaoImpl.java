package net.endpoint.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.PhoneDto;
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
	 *<p> Update profile from Profile Dto </p>
	 */
	@Override
	public boolean updateProfile(ProfileDto profiledto) {	
		boolean result = false;
	 if(profiledto!=null){
	    Person person = findByUserName(profiledto.getEmail());
		 if(person!=null){
			person = person!=null ? person : new Person();
			if(!profiledto.getFirstname().isEmpty()){
			    person.setFirstName(profiledto.getFirstname());
			    }
			if(!profiledto.getLastLogin().isEmpty()){
				person.setLastName(profiledto.getLastname());
				}
			if(profiledto.getDob() != null){
				   	person.setDateOfBirth(profiledto.getDob());
				   	}
			 person.setUpdatedAt(new Date());
		   save(person);
		return true;
		 }
	  }
	 return result;
	}
	
	
	private Address loadAddress(long id){
		return this.sessionFactory.getCurrentSession().load(Address.class, id);
	}
	
	private Phone loadPhone(long id){
		return this.sessionFactory.getCurrentSession().load(Phone.class, id);
	}
	
	private void save(Object o){
		sessionFactory.getCurrentSession().saveOrUpdate(o);
	}


/**
 * 
 */
	@Override
	public Address addOrUpdateAddtess(AddressDto addressdto) {
		Address ad  = null;
		if(addressdto!=null && addressdto.id!= null ){
			 ad  =  loadAddress(Long.parseLong(addressdto.id));
			 if(ad!=null){
		        ad =  addressdto.convertTo(ad);	    
		       this.save(ad);
		   }
		}	
	return ad;
	}


	/**
	 * <p>Save Or update phone</p>
	 * @return Phone  
	 * 
	 */
	@Override
	public Phone addOrUpdatePhone(PhoneDto phonedto) {
		Phone result = null;
		if(phonedto!=null && phonedto.serverid!=null){
			 result = loadPhone(Long.parseLong(phonedto.serverid));
			 result = phonedto.convertTo(result);	
			this.save(result);
		}	
		return result;
	}
	
	

}
