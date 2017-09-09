package endpoint;


import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
		assertNotNull(etd);
		//byte[] file =  etd.getAttachments().get(0).getAttachedFile();
		
		
		
	}

	
	
	
	
	
}
