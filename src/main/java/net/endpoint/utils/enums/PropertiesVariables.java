package net.endpoint.utils.enums;

public interface PropertiesVariables {

	
	public enum GLOBAL_PROPERTIES{
		
		 ENCODE_UTF_8("UTF-8");
	
		GLOBAL_PROPERTIES(String value){ this.value = value;}
		private String value;
		public String getValue() { return this.value;}
	}
	
	public enum ENCODER_PROPERTIES {
		
		 ENCODE_SHA_256("SHA-256"),
		// algorithm 
		 ALGORITHM_AES("AES"),
		 ENCODER_KEY("a 5 4 3 7 8 9 h 2 3 b 2 7 6 7 t"); // 16 bit key 
		
		private String value;
		
		private ENCODER_PROPERTIES(String value) {
			this.value = value;
		}

		public String getValue() { return this.value;}
	}
	
	
}
