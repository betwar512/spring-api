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
		return Crypt.crypt(rawPassword.toString());
	}

	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
	return	encodedPassword
			.equals(Crypt
					   .crypt(rawPassword
							       .toString()
							           .getBytes(), encodedPassword));
	}


}
