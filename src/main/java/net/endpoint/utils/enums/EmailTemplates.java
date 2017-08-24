package net.endpoint.utils.enums;



public interface EmailTemplates {
	
 public static final String PNG_MIME = "image/png";
 public static final String ENCODE_UTF_8 = "UTF-8";
	
 public enum EmailTemplatesUrls {
	TEMPLATE_NAME("url");
	private final String url;

	private EmailTemplatesUrls(String url) {
		this.url = url;
	  }
	
	  public String getUrl() {
		return this.url;
	  }
   }



}