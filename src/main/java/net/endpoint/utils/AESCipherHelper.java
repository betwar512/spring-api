package net.endpoint.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

import net.endpoint.utils.enums.PropertiesVariables.*;

public abstract class AESCipherHelper {

	private static final Logger logger = Logger.getLogger(AESCipherHelper.class);
	 private AESCipherHelper() {
		    throw new IllegalStateException("Utility class");
		  }

	  // Performs Encryption
    public static String encrypt(String plainText) {  
    	   Key key = generateKey();
			Cipher chiper;
			try {
				chiper = Cipher.getInstance(ENCODER_PROPERTIES.ALGORITHM_AES.getValue());
				   chiper.init(Cipher.ENCRYPT_MODE, key);
		            byte[] encVal = chiper.doFinal(plainText.getBytes());
		            byte[]  encryptedValue =  Base64.getEncoder().encode(encVal);
		            return  new String(encryptedValue);
			   } catch (NoSuchAlgorithmException | 
					      NoSuchPaddingException | 
					         InvalidKeyException |
					   IllegalBlockSizeException | 
						     BadPaddingException e) {
				                           logger.error(e);
			          }
			
		return null;      
    }

    // Performs decryption
    public static String decrypt(String encryptedText) {
            // generate key 
            Key key = generateKey();
            Cipher chiper;
			try {
				chiper = Cipher.getInstance(ENCODER_PROPERTIES.ALGORITHM_AES.getValue());
				  chiper.init(Cipher.DECRYPT_MODE, key);
		            byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
		            byte[] decValue = chiper.doFinal(decordedValue);
		            return new String(decValue);
			} catch (NoSuchAlgorithmException |
					   NoSuchPaddingException | 
					      InvalidKeyException | 
					IllegalBlockSizeException | 
					      BadPaddingException e) {
				logger.error(e);
			}
          
			return "";
    }
	
  //generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey()  {
      return new SecretKeySpec(
    		  ENCODER_PROPERTIES.ENCODER_KEY.getValue().replaceAll("\\s","").getBytes() ,
    		  ENCODER_PROPERTIES.ALGORITHM_AES.getValue() );
    }
    
    /**
     * <p> UUID v4 using SHA_256 Encoder </p>
     * @return String 
     */
	public static String generateUUID() {
		
		MessageDigest salt;
		try {
			salt = MessageDigest.getInstance(ENCODER_PROPERTIES.ENCODE_SHA_256.getValue());
			salt.update(UUID.randomUUID().toString().getBytes(GLOBAL_PROPERTIES.ENCODE_UTF_8.getValue()));
			return  bytesToHex(salt.digest());
		} catch (NoSuchAlgorithmException |
			 UnsupportedEncodingException e) {
		logger.error(e);
		}
		
		return "";
	}
	
	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		    StringBuilder builder = new StringBuilder();
		    for (byte b: bytes) {
		      builder.append(String.format("%02x", b));
		    }
		    return builder.toString();
		  }
    
	
}
