package net.endpoint.filesystem;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.filesystem.model.FsProfile;
import net.endpoint.filesystem.repository.FsProfileDocumentRepsitory;
import net.endpoint.filesystem.repository.FsProfileRepository;
import net.endpoint.main.MainController;

@RestController
@RequestMapping("/fs/")
public class FsDocumentController extends MainController {
	
	@Autowired
	FsProfileDocumentRepsitory fsReposetory;
	@Autowired
	FsProfileRepository fsProfileRepo;
	
	@RequestMapping(value="all",method = RequestMethod.GET)
	public String getAll() {	

		
		
		return "Fs controller active";
	}

	public void setFsReposetory(FsProfileDocumentRepsitory fsReposetory) {
		this.fsReposetory = fsReposetory;
	}

	
	
	
	
	
}
