package net.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.endpoint.dao.ProfileDao;
import net.endpoint.dto.account.ProfileDto;
import net.endpoint.model.account.Person;

@Service
public class GlobalSettingsServiceImpl implements GlobalSettingsService {
	@Autowired
	ProfileDao profileDao;

	 public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}


	@Override
	@Transactional(readOnly=true)
	public ProfileDto loadProfile(String email) {
			Person person = profileDao.findByUserName(email);
			ProfileDto p = new ProfileDto();
		    p.pars(person);
		    return p;

		}
	
}

