package net.endpoint.util;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;

/**
 * 
 * <p> Helper class for testing String format and Date Utils + Decoded and encoder </p>
 * @author A.H.Safaie
 *
 */
public abstract class HelpUtil {
	
	public static final SimpleDateFormat DATETIME = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
   public boolean isNullOrEmpty(String str){
   	return str!=null && !str.isEmpty() ? false : true;
  }
	
   
   public static String Encode64(long id){
	   byte[] bytes = longToBytes(id);
	   byte[] encoded = Base64.getEncoder().encode(bytes);	
	   return  Arrays.toString(encoded);
   }
   
   public static long Decode64(String id){
	     byte[]   bytes = id.getBytes();
	     byte[] decoded = Base64.getDecoder().decode(bytes);     
	     return bytesToLong(decoded);
   }

   
  //Long to Byte Converters 
   public static byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.put(bytes);
	    buffer.flip();//need flip 
	    return buffer.getLong();
	}
   
   
}
