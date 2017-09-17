package net.endpoint.filesystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import net.endpoint.filesystem.model.FsProfile;

public interface FsProfileRepository extends MongoRepository<FsProfile, String> {
	

}
