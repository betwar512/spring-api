package net.endpoint.filesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.endpoint.filesystem.dto.DocumentDto;
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
	public String getAll(@RequestParam("files") MultipartFile[] uploadfiles) {	

		System.out.println(uploadfiles.length);
		
		return "Fs controller active";
	}

	@RequestMapping(value="/upload/documents" , method = RequestMethod.POST)
	public void upploadDocuments(DocumentDto[] documents) {
		
		if(documents != null) {
			for(DocumentDto m: documents) {
				System.out.println(m.toString());
			}
		}
		
		
	}
	
	public void setFsReposetory(FsProfileDocumentRepsitory fsReposetory) {
		this.fsReposetory = fsReposetory;
	}

	
	
	
	
	
}
