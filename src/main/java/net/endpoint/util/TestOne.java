package net.endpoint.util;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.junit.Test;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestOne {

	@Test
	public void test() throws NoSuchAlgorithmException, NoSuchProviderException {

		String pass = "4650080";
		 MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(pass.getBytes());

	        byte byteData[] = md.digest();

	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

	        System.out.println("Hex format : " + sb.toString());

		
	
	String cryptedPassword = new ShaPasswordEncoder(512).toString();//.encodePassword("4650080", null);
	System.out.println(cryptedPassword);
	assertNotNull(cryptedPassword);
	}
	//'$6$d324a08b9997f5d0$d.6MPhqdyl4PW2REzmRbfO20zRJzoR2X3FPikzYhQkxeJMb123adkcsDKx0vk91e1ELKL63oSR0jNIV/zIt5n/', 'admin@skinqualitycare.com.au', NULL, '2017-02-04 16:15:46', 'admin', '7HqUusLPSoxcjWlHKdP7vEP5Fw4qqPSrODqptZmngPIwKi8vj395S3TBp0Hz'

	public String geranteSalt(){
		
		
		
		return null;
	}
	
	
}
