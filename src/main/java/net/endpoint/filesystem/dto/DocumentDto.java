package net.endpoint.filesystem.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentDto  {

	private String fileName;
    private Long fileSize;
    private String fileType;
    private MultipartFile file;



	public String getFileName() {
		return fileName;
	}
    public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}




	@Override
	public String toString() {
		return "DocumentDto [fileName=" + fileName + ", fileSize=" + fileSize + ", fileType=" + fileType + "]";
	}
    
    
}
