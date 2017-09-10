package endpoint;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import net.endpoint.account.dao.ProfileDao;
import net.endpoint.account.model.Person;
import net.endpoint.emailtemplate.dto.EmailTemplateDto;
import net.endpoint.emailtemplate.dto.SendEmailDto;
import net.endpoint.emailtemplate.model.EmailTemplate;
import net.endpoint.emailtemplate.service.EmailTemplateService;


public class EmailTemplateTest extends DatabaseTest {
	@Autowired
	ProfileDao profileDao;
	@Autowired
	EmailTemplateService emts;
	
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	EmailTemplate et = this.sessionFactory.getCurrentSession().load(EmailTemplate.class, 1l);
		EmailTemplateDto edto=et.pars();
		SendEmailDto emailDto = new SendEmailDto("admin@skinqualitycare.com.au","betwar512@gmail.com",
												 "Welcome To System","", null, new Date(),null,null);
		try {
			emts.sendEditableTemplateEmail(edto, emailDto);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Person u = profileDao.findByUserName("info@skinqualitycare.com.au");
//		EmailTemplateDto etd = new EmailTemplateDto("", "Test Send", new ArrayList<>(), new ArrayList<>());
		assertNotNull(u);
		//byte[] file =  etd.getAttachments().get(0).getAttachedFile();
	}

	
	
	private void readFile(){}
	
	private void writeToString(){}
	
	
	
	
	
	
}
