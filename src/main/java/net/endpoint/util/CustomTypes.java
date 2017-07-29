package net.endpoint.util;


/**
 * Abstract class Helper to Keeping all enum types in a same class before moving to db </p>
 * @author A.H.Sfaie
 *
 */
public abstract class CustomTypes {

	/**
	 * <p>Types for accounts for security level mapping later on on db </p>
	 * @author A.H.Safaie
	 *
	 */
	public enum ACCOUNT_TYPES{
		
	}
	
	public enum DOMAINS{
		EndPoint("betwarendpoint.net"),
		SkinQuality("skinqualitycare.com.au");
		private String domain;
		DOMAINS(String domain){
			this.domain = domain;
		}
		public String getDomain(){
			return this.domain;
		}
	}
	
	public enum SECURITY_ROLE{
		ADMIN("Admin"),
		USER("User");
		private String role;
		SECURITY_ROLE(String role){
			this.role = role;
		}
		public String getRole(){
			return this.role;
		}
	}
	
	
	
	/**
	 * <p>Types for Phone and Address </p>
	 * @author A.H.Safaie
	 *
	 */
	public enum ACCOUNT_DETAIL{
		HOME(1),WORK(2);
		
	private final Integer code;
	
		private ACCOUNT_DETAIL(Integer code){
			this.code = code;
		 }

		public Integer getCode(){
			return this.code;
		}
	 }
	
	
}
