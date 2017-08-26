package net.endpoint.emailtemplate.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class EmailTemplateAttachmentDto {

	private String name;
	private String htmlFiledId;
	private MultipartFile attachedFile;
	private String fileType;

	public EmailTemplateAttachmentDto(String name, String htmlFiledId, String fileType, File attachedFile) {
		super();
		this.name = name;
		this.htmlFiledId = htmlFiledId;
	try {
		this.attachedFile = this.writeToMultiPartFile(attachedFile);
	          } catch (IOException e) {
		   e.printStackTrace();
	         }
		this.fileType = fileType;
	}

	
	private MultipartFile writeToMultiPartFile(File file) throws IOException{
		FileInputStream input = new FileInputStream(file);	
		return new MockMultipartFile(this.name,
		            file.getName(), "text/plain", IOUtils.toByteArray(input));
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtmlFiledId() {
		return htmlFiledId;
	}

	public void setHtmlFiledId(String htmlFiledId) {
		this.htmlFiledId = htmlFiledId;
	}

	public MultipartFile getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(MultipartFile attachedFile) {
		this.attachedFile = attachedFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
