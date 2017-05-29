package net.endpoint.unittest;

import static org.junit.Assert.*;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.hibernate.annotations.common.util.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.endpoint.util.HelpUtil;

public class FirstTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void encode64(){
		
		long num = 123456;
		 String encoded =  HelpUtil.Encode64(num);
		 System.out.println(encoded);
		assertNotNull(encoded);
	}
	
	@Test
	public void test() {
		
		Long id = new Long(1234567);
		EncryptClass cl = null;
		try {
			cl = new EncryptClass();
		} catch (Exception e) { e.printStackTrace(); }
		String en = cl.encrypt(id.toString());
		String decode =	cl.decrypt(en);
		System.out.println(en);
		System.out.println(decode);
		assertEquals(id.toString(),decode);
		
		
	}
	
	
	public void decrept_test(){
		
	}
	
	
	public class EncryptClass{
		
		   private static final String UNICODE_FORMAT = "UTF8";
		    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
		    private KeySpec ks;
		    private SecretKeyFactory skf;
		    private Cipher cipher;
		    byte[] arrayBytes;
		    private String myEncryptionKey;
		    private String myEncryptionScheme;
		    SecretKey key;

		    public EncryptClass() throws Exception {
		        myEncryptionKey = "ThisIsSpartaThisIsSparta";
		        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		        ks = new DESedeKeySpec(arrayBytes);
		        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		        cipher = Cipher.getInstance(myEncryptionScheme);
		        key = skf.generateSecret(ks);
		    }


		    public String encrypt(String unencryptedString) {
		        String encryptedString = null;
		        try {
		            cipher.init(Cipher.ENCRYPT_MODE, key);
		            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
		            byte[] encryptedText = cipher.doFinal(plainText);
		            encryptedString = new String(Base64.getEncoder().encode(encryptedText));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return encryptedString;
		    }


		    public String decrypt(String encryptedString) {
		        String decryptedText=null;
		        try {
		            cipher.init(Cipher.DECRYPT_MODE, key);
		            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
		            byte[] plainText = cipher.doFinal(encryptedText);
		            decryptedText= new String(plainText);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return decryptedText;
		    }
		
	}
	

}
