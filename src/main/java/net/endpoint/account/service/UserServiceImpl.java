package net.endpoint.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import net.endpoint.account.dao.ProfileDao;
import net.endpoint.account.dao.UserDaoImpl;
import net.endpoint.account.dto.AccountRequestDto;
import net.endpoint.account.dto.AddressDto;
import net.endpoint.account.dto.PhoneDto;
import net.endpoint.account.dto.ProfileDto;
import net.endpoint.account.model.Address;
import net.endpoint.account.model.Person;
import net.endpoint.account.model.Phone;
import net.endpoint.account.model.SecurityRole;
import net.endpoint.account.model.User;
import net.endpoint.main.model.Domain;
import net.endpoint.utils.AESCipherHelper;
import net.endpoint.utils.CustomEncoder;
import net.endpoint.utils.enums.EnumTypes.SECURITY_ROLE;

@Service
@Transactional
public class UserServiceImpl implements UserService , UserDetailsService {

	@Autowired
	UserDaoImpl userDao;
	@Autowired
	ProfileDao profileDao;
	
	
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	
	public void setProfileDao(ProfileDao profileDao){
		this.profileDao = profileDao;
	}
	
	@Transactional(readOnly=true)
	public List<User> getAll(){
		return this.userDao.getAll();
	}

	public List<ProfileDto> getAllProfileDtos(){
		
    List<ProfileDto> profiles = new ArrayList<>();		  
		  this.getAll().forEach(t->{
			  if(t.getPerson() != null) {
			 ProfileDto pr =   new ProfileDto();
			 pr.pars(t.getPerson());
			  profiles.add(pr);		  
			  }
		  });
		return profiles;
	}

	@Transactional(readOnly=true)
	public User get(long id) {
		return userDao.get(id);
	}



	@Transactional(readOnly=true)
	public User findByName(String name) {
		return userDao.findbyName(name);

	}


	@Override
	@Transactional(readOnly=true)
	public ProfileDto findByUserName(String name) {
		Person person=  profileDao.findByUserName(name);
		ProfileDto pdto = new ProfileDto();
		pdto.pars(person);
		return pdto;
	}

	@Override
	@Transactional
	public void updateProfile(ProfileDto profiledto) {

		 if(profiledto != null){
		
		    Person person = this.profileDao.findByUserName(profiledto.getEmail());
			User     user = person != null ? person.getUser() : this.userDao.findbyName(profiledto.getEmail());
			   if(person == null) {
				   person  = new Person();
					person.setCreatedAt(new Date());
					person.setUser(user);
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
		 this.profileDao.save(person);
		  }

	}

	@Override
	@Transactional
	public boolean createOrUpdateAddress(AddressDto address, String userName) {
		Address ad  = null;
	if(address!=null) {	
		if(address.id!= null ){
			 ad  =  this.profileDao.loadAddress(Long.parseLong(address.id));
		      ad =  address.convertTo(ad);
			 } else {
				 Person p = this.profileDao.findByUserName(userName);
				 if(p.getAddresses().size() < 3) {
					ad =  address.convertTo(null);
					ad.setPerson(p);
				 }
			 }
		   this.profileDao.updateAddress(ad);
		   return true;
		  }
	  return false;
	}

	@Override
	@Transactional
	public boolean createOrUpdatePhone(PhoneDto phonedto,String userName) {
		Phone result = null;
		if(phonedto!=null){ 
			if( phonedto.serverid!=null){
			 result = this.profileDao.loadPhone(Long.parseLong(phonedto.serverid));
			 result = phonedto.convertTo(result);	
		      }	else {
			  Person p = this.profileDao.findByUserName(userName);
			  System.out.println("Person:" + p.toString());
			    result =  phonedto.convertTo(null);
			    result.setPerson(p);
		  }
		
		 this.profileDao.updatePhone(result);
		 return true;
		}
		 return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		if(!StringUtils.isEmpty(password)){
			CustomEncoder encoder = new CustomEncoder();
			String hash = encoder.encode(password);
			user.setPassword(hash);
		    this.userDao.update(user);
		    return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean createAccount(AccountRequestDto accountRequestDto) throws Exception {
		 User       user = null;
		 String userName = accountRequestDto.userName + accountRequestDto.domainName ;
		            user = this.userDao.findbyName(userName);
		     
	if(	 user == null){
			    user = new User();
				user.setUserName(accountRequestDto.userName);
				Domain domain = this.userDao.getDomainByName(accountRequestDto.domainName);		
	    if(domain!=null){
		   user.setDomain(domain);
		   boolean passwordIsValid = !StringUtils.isEmpty(accountRequestDto.password) && accountRequestDto.password.equals(accountRequestDto.rePassword) ;
			
		   if(passwordIsValid){
				CustomEncoder   cr = new CustomEncoder();
				String encodedPass = cr.encode(accountRequestDto.password);
				user.setEmail(accountRequestDto.userName +"@"+ accountRequestDto.domainName);
				user.setPassword(encodedPass);
				user.setCreatedAt(new Date());
				user.setUpdatedAt(new Date());
				user.setRegisteredEmail(accountRequestDto.registerationEmail);
				
				SECURITY_ROLE role = SECURITY_ROLE.valueOf(accountRequestDto.role.toUpperCase());
				SecurityRole    sr = this.userDao.loadSecurityRole(role);
				user.getRolse().add(sr);		
				user.setEmailPassword(AESCipherHelper.encrypt(accountRequestDto.password));
				this.userDao.update(user);
				
				Person person = new Person();
				person.setFirstName(accountRequestDto.firstName);
				person.setLastName(accountRequestDto.lastName);
				person.setDateOfBirth(accountRequestDto.dob);	
				person.setCreatedAt(new Date());
				person.setUpdatedAt(new Date());
				person.setUser(user);
				this.profileDao.update(person);
				return true;
				
				  }//password
			   }//domain 
			}//dto
	return false;
	 }

	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return   this.userDao.findbyName(username);
	}


}
