package net.endpoint.utils.enums;



public interface EmailVariables {
	
	public static final String themplate_url = "";
   
/**
 * 
 * @author A.H.Safaie
 *
 */
 public enum EmailContentProperties {
	 HTML5("HTML5"),
	 ENCODE_UTF_8("UTF-8"),
	 PNG_MIME("image/png");
	 private final String value;
	 private EmailContentProperties(String value) {
		 this.value  = value;
	 }
	 public String getValue() {
		 return this.value;
	 }
 }
 
 
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