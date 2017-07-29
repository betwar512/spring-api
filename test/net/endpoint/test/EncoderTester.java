package net.endpoint.test;

import static org.junit.Assert.*;

import org.junit.Test;

import net.endpoint.util.AESCipherHelper;
import net.endpoint.util.CustomEncoder;

public class EncoderTester {

	@Test
	public void test() {
			CustomEncoder encoder = new CustomEncoder();
		String encoded = 	encoder.encode("Solmaz662M");
		System.out.println(encoded);
		
	}

	
	
	
	@Test
	public void testEncoder(){
		final String pass= "123456Abbas";
		
		try {
	String encripter=		AESCipherHelper.encrypt(pass);
	System.out.println("Salt result :" + encripter);
	String str = AESCipherHelper.decrypt(encripter);
	System.out.println("Decr:" + str);
	assertEquals(str,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void decode(){
		String encripter = "Ln9DnPnikP6eaiyv2j+ksw==";
		String str;
		try {
			str = AESCipherHelper.decrypt(encripter);
			System.out.println("Decr:" + str);
			assertEquals(str,encripter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
}
