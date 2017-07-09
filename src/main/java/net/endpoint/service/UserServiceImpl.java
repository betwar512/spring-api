package net.endpoint.service;

import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.mysql.cj.api.xdevapi.Result;
import com.mysql.cj.core.util.StringUtils;

import net.endpoint.dao.ProfileDao;
import net.endpoint.dao.UserDao;
import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.PhoneDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.Domain;
import net.endpoint.model.User;
import net.endpoint.model.account.Address;
import net.endpoint.model.account.Person;
import net.endpoint.model.account.Phone;
import net.endpoint.util.CustomEncoder;

public class UserServiceImpl implements UserService {

	
	UserDao userDao;
	ProfileDao profileDao;
	TransactionTemplate transactionTemplate;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setProfileDao(ProfileDao profileDao){
		this.profileDao = profileDao;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
		}


	@Transactional(readOnly=true)
	public List<User> getAll(){
		List<User> result=this.userDao.getAll();
		return result;
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
	public AddressDto createOrUpdateAddress(AddressDto address) {
		Address ad  = null;
		if(address!=null && address.id!= null ){
			 ad  =  this.profileDao.loadAddress(Long.parseLong(address.id));
		      ad =  address.convertTo(ad);
			 }

			
		
		
	  return null;
	}

	@Override
	@Transactional
	public boolean createOrUpdatePhone(PhoneDto phonedto) {
		Phone result = null;
		if(phonedto!=null && phonedto.serverid!=null){
			 result = this.profileDao.loadPhone(Long.parseLong(phonedto.serverid));
			 result = phonedto.convertTo(result);	
			 this.profileDao.updatePhone(result);
			 return true;
		}	
		 return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		if(!StringUtils.isNullOrEmpty(password)){
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
	public boolean createAccount(AccountRequestDto accountRequestDto) {
		 User       user = null;
		 String userName = accountRequestDto.userName + accountRequestDto.domainName ;
		            user = this.userDao.findbyName(userName);
		     
	if(	 user == null){
			    user = new User();
				user.setUserName(accountRequestDto.userName);
				Domain domain = this.userDao.getDomainByName(accountRequestDto.domainName);		
			 if(domain!=null){
				user.setDomain(domain);
				boolean passwordIsValid = !StringUtils.isNullOrEmpty(accountRequestDto.password) &&
						                   accountRequestDto.password.equals(accountRequestDto.rePassword) ;
			  if(passwordIsValid){
				CustomEncoder   cr = new CustomEncoder();
				String encodedPass = cr.encode(accountRequestDto.password);
				user.setEmail(accountRequestDto.userName +"@"+ accountRequestDto.domainName);
				user.setPassword(encodedPass);
				user.setCreatedAt(new Date());
				user.setUpdatedAt(new Date());
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


	public boolean isUserExist(String username, String domainName) {
		// TODO Auto-generated method stub
		return false;
	}




}
