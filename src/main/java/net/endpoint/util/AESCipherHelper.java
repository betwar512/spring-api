package net.endpoint.util;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCipherHelper {

	 private AESCipherHelper() {
		    throw new IllegalStateException("Utility class");
		  }

	private static String  algorithm = "AES";
	private static byte[] keyValue=new byte[] {'0','2','3','4','5','6','7','8','9','1','2','3','4','5','6','7'};// your key
	  // Performs Encryption
    public static String encrypt(String plainText) throws Exception {  
    	   Key key = generateKey();
			Cipher chiper = Cipher.getInstance(algorithm);
            chiper.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = chiper.doFinal(plainText.getBytes());
            byte[]  encryptedValue =  Base64.getEncoder().encode(encVal);
            return  new String(encryptedValue);
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception {
            // generate key 
            Key key = generateKey();
            Cipher chiper = Cipher.getInstance(algorithm);
            chiper.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
            byte[] decValue = chiper.doFinal(decordedValue);
            return new String(decValue);
    }
	
  //generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey()  {
      return new SecretKeySpec(keyValue, algorithm);
    }
	
}
