package net.endpoint.filesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.endpoint.filesystem.model.FsProfile;
import net.endpoint.filesystem.repository.FsProfileRepository;

@Service
public class FsServiceImpl implements FsService {
	@Autowired
	public FsProfileRepository profileReposetory;
	@Autowired
	public NextSequenceService nextSeq;
	
	@Override
	public void addNewProfile(FsProfile profile) {
	profile.setProfileId(nextSeq.getNextSequence("profiles"));
	profileReposetory.save(profile);
	}

	@Override
	public List<FsProfile> getAll() {	
		return this.profileReposetory.findAll();
	}

	
	
	
}
