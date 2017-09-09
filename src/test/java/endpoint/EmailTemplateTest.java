package endpoint;


import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import net.endpoint.account.dao.ProfileDao;
import net.endpoint.account.model.Person;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;

@RunWith(SpringRunner.class)
public class EmailTemplateTest {
	@Autowired
	ProfileDao profileDao;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Person u = profileDao.findByUserName("info");
		
		EmailTemplateDto etd = new EmailTemplateDto("", "Test Send", new ArrayList<>(), new ArrayList<>());
		assertNotNull(u);
		//byte[] file =  etd.getAttachments().get(0).getAttachedFile();
		
		
		
	}

	
	
	
	
	
}
