package net.endpoint.filesystem.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
@Service
public class FsGridFsServiceImpl implements FsGridFsService {
	@Autowired
	private GridFsTemplate gridTemplate;
	
	@Override
	public void saveFile(File file, Object object) {
		//this.gridTemplate.store(, metadata)
		
	}

	public void setGridTemplate(GridFsTemplate gridTemplate) {
		this.gridTemplate = gridTemplate;
	}
	
	

}
