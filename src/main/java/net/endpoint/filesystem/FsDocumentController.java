package net.endpoint.filesystem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.endpoint.main.MainController;

@RestController
@RequestMapping("/api/fs/")
public class FsDocumentController extends MainController {
	
	public String getAll() {
		return "Fs controller active";
	}

	
}
