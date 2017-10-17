package net.endpoint.filesystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.endpoint.filesystem.model.FsProfileDocument;

public interface FsProfileDocumentRepsitory extends MongoRepository<FsProfileDocument,String> {

	public FsProfileDocument findByName(String name);
	public List<FsProfileDocument> findByOwnerProfile(String ownerProfile);
	
}
