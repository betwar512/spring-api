package net.endpoint.filesystem.service;

import java.util.List;

import net.endpoint.account.dto.ProfileDto;
import net.endpoint.filesystem.model.FsProfile;

public interface FsService {

	public void addNewProfile(FsProfile profile);
	public List<FsProfile> getAll();
	
}
