package net.endpoint.unittest;

import static org.junit.Assert.*;

import java.util.Base64;

import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.Md5Crypt;
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
	public void test() {
		
		Long id = new Long(1234567);
		String encoded =	 Md5Crypt.md5Crypt(id.toString().getBytes());
		  //  String decoded = Md5
		System.out.println(encoded);
		assertNotNull(encoded);
		
		
	}

}
