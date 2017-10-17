package net.endpoint.filesystem.service;

import java.util.List;
import net.endpoint.filesystem.model.FsProfile;
import net.endpoint.filesystem.model.FsProfileDocument;

public interface FsService {

	public void addNewProfile(FsProfile profile);
	public List<FsProfile> getAll();
	public void saveDocument(FsProfileDocument fsProfileDucment,String id);
	public  List<FsProfileDocument> getAllDocuments();
	public  List<FsProfileDocument> findProfileDocuments(String profileId);
	
}
