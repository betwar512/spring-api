package net.endpoint.unittest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import net.endpoint.emailtemplate.dto.EmailTemplateDto;

public class EmailTemplateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		EmailTemplateDto etd = new EmailTemplateDto("", "Test Send", new ArrayList<>(), new ArrayList<>());
		
		MultipartFile file =  etd.getAttachments().get(0).getAttachedFile();
		
		
		
	}

	
	
	
	
	
}
