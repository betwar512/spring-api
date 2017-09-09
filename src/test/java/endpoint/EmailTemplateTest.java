package endpoint;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import net.endpoint.account.dao.ProfileDao;
import net.endpoint.account.model.Person;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;


public class EmailTemplateTest extends DatabaseTest {
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
		Person u = profileDao.findByUserName("info@skinqualitycare.com.au");
//		EmailTemplateDto etd = new EmailTemplateDto("", "Test Send", new ArrayList<>(), new ArrayList<>());
		assertNotNull(u);
		//byte[] file =  etd.getAttachments().get(0).getAttachedFile();
	}

	
	
	private void readFile(){}
	
	private void writeToString(){}
	
	
	
	
	
	
}
