package net.endpoint.util;

import org.apache.commons.codec.digest.Crypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>Custom Encoder to Read and comapre salt password with encrtyp SHA-512 <p>
 * <p>Using Apache Crypt commons library  </p>
 * @author A.H.Safaie
 *
 */
public class CustomEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		
		return Crypt.crypt( ((String)rawPassword).getBytes());
	}

	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
//		System.out.println(rawPassword);
//		System.out.println(encodedPassword);
//		System.out.println("Cypher:" + Crypt.crypt(rawPassword.toString().getBytes(),encodedPassword));
//		boolean isB = encodedPassword.equals(Crypt.crypt(rawPassword.toString().getBytes(),encodedPassword));
//		System.out.println("Pass is: " + isB);
		
	return	encodedPassword.equals(
			Crypt.crypt(rawPassword.toString().getBytes(),encodedPassword)
			);
	
	
	
	}
	



}
