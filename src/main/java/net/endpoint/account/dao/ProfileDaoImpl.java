package net.endpoint.account.dao;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Address;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.Phone;
import net.endpoint.account.model.User;
import net.endpoint.main.dao.BaseDao;
@Repository
public class ProfileDaoImpl extends BaseDao implements ProfileDao {


	
	@Override
	public Person load(long id) {
		return this.getSession().load(Person.class, id);
	}

	@Override
	public Person findByUser(User user) {
			@SuppressWarnings("unchecked")
			List<Person> list=	this.getSession()
				.createQuery("from Person p where p.user = :user")
				.setParameter("user", user).list();					
		return list !=null && !list.isEmpty() ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Person findByUserName(String email) {
		System.out.println(email);
		 List<Person> list = this.getSession()
				 						   .createQuery("from Person p where p.user.email=:email")
				 						   .setParameter("email", email).list();
		 return list!=null && !list.isEmpty() ? list.get(0) : null;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
   private User getUser(String email){
		UserDaoImpl dao =   new UserDaoImpl();		
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
				if(user!=null){
					person.setUser(user);
				}
				this.getSession().save(person);
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
		return this.getSession().load(Address.class, id);
	}
	@Override
	public Phone loadPhone(long id){
		return this.getSession().load(Phone.class, id);
	}
	@Override
	public void save(Object o){
		this.getSession().saveOrUpdate(o);
	}

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
		this.getSession().saveOrUpdate(person);
		
	}
	
	

}
