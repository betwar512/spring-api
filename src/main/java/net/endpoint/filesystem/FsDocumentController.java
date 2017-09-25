package net.endpoint.filesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.endpoint.filesystem.repository.FsProfileDocumentRepsitory;
import net.endpoint.filesystem.repository.FsProfileRepository;
import net.endpoint.main.MainController;

@RestController
@RequestMapping("/api/fs")
public class FsDocumentController extends MainController {
	
	@Autowired
	FsProfileDocumentRepsitory fsReposetory;
	@Autowired
	FsProfileRepository fsProfileRepo;
	
	@RequestMapping(value="/all",method = RequestMethod.POST)
	public String getAll(@RequestParam("file") MultipartFile uploadfile) {	

		System.out.println(uploadfile.getContentType());
		
		return "Fs controller active";
	}

	public void setFsReposetory(FsProfileDocumentRepsitory fsReposetory) {
		this.fsReposetory = fsReposetory;
	}

	
	
	
	
	
}
