package net.endpoint.service;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import net.endpoint.dao.ProfileDao;
import net.endpoint.dao.UserDao;
import net.endpoint.dto.account.AccountRequestDto;
import net.endpoint.dto.account.AddressDto;
import net.endpoint.dto.account.PhoneDto;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.User;
import net.endpoint.model.account.Address;
import net.endpoint.model.account.Person;
import net.endpoint.model.account.Phone;

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
	public void updateProfile(ProfileDto profileDto) {
		this.profileDao.updateProfile(profileDto);
	}

	@Override
	@Transactional
	public AddressDto createOrUpdateAddress(AddressDto address) {
		Address ad =	profileDao.addOrUpdateAddtess(address);
		AddressDto result = new AddressDto();
		result.pars(ad);
		return result;
	}

	@Override
	@Transactional
	public PhoneDto createOrUpdatePhone(PhoneDto phonedto) {
		  Phone p =   profileDao.addOrUpdatePhone(phonedto);
		  	phonedto.pars(p);
		  	return phonedto;
	}

	@Override
	public User updatePassword(User user, String password) {
		return	userDao.changePassword(user, password);
	}

	@Override
	public boolean createAccount(AccountRequestDto accountRequestDto) {
		   return   this.userDao.create(accountRequestDto);
	 }
	

}
