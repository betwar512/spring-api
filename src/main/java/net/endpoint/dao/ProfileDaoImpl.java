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
	public void updateProfile(ProfileDto profiledto) {	
		Person person = findByUserName(profiledto.getEmail());
		person = person!=null ? person : new Person();
		if(!profiledto.getFirstname().isEmpty())
		    person.setFirstName(profiledto.getFirstname());
		if(!profiledto.getLastLogin().isEmpty())
			person.setLastName(profiledto.getLastname());
		if(profiledto.getDob() != null)
			   	person.setDateOfBirth(profiledto.getDob());
		if(person.getCreatedAt() == null) person.setCreatedAt(new Date());
		person.setUpdatedAt(new Date());
		save(person);
		
		if(profiledto.getAddresses()!=null){
		 for(AddressDto a :profiledto.getAddresses()){		 
		  Address   ad =loadAddress(Long.parseLong(a.getId()));
		   ad =  a.convertTo(ad);
		   ad.setPerson(person);
		   save(ad);
		   person.getAddresses().add(ad);
		  
		   }
		}
		if(!profiledto.getPhones().isEmpty()){
			for(PhoneDto pdto:profiledto.getPhones()){
				Phone p =loadPhone(Long.parseLong(pdto.getServerid()));
				p = pdto.convertTo(p);
				p.setPerson(person);
				save(p);
				person.getPhones().add(p);	
			}
		}
		save(person);
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
	
	

}
