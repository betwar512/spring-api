package net.endpoint.util;

import java.text.SimpleDateFormat;

public class HelpUtil {
	
	public static SimpleDateFormat DATETIME = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
   public boolean isNullOrEmpty(String str){
   	return str!=null && !str.isEmpty() ? false : true;
  }
	

   
   
   
}
