package endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import net.endpoint.emailtemplate.model.EmailTemplate;
import net.endpoint.emailtemplate.model.EmailTemplateField;
import net.endpoint.utils.enums.EmailVariables.EmailContentProperties;

@Transactional
public class DatabaseTest extends ApplicationTest {

	

    @Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	public SessionFactory sessionFactory;
    
    
	@Test
	public void saveEmailTemplateINtoDb(){
		EmailTemplate em = (EmailTemplate)this.sessionFactory.getCurrentSession().load(EmailTemplate.class, 1l);
	//	this.sessionFactory.getCurrentSession().flush();
		System.out.println("Id"+em.getId());
		EmailTemplateField emp = new EmailTemplateField();
		emp.setHtmlFieldId("email");
		emp.setName("email");
		emp.setFieldContent("defualt");
		emp.setTemplate(em);
		this.sessionFactory.getCurrentSession().save(emp);
		em.getFildsContents().add(emp);
	

		
		EmailTemplateField emp2 = new EmailTemplateField();
		emp2.setHtmlFieldId("name");
		emp2.setName("name");
		emp2.setFieldContent("defualt");
		emp2.setTemplate(em);
		em.getFildsContents().add(emp2);
		this.sessionFactory.getCurrentSession().save(emp2);
		this.sessionFactory.getCurrentSession().save(em);
		this.sessionFactory.getCurrentSession().getTransaction().commit();
	
	}
	
	
    /* 
     * Send HTML mail with in-line image
     */
    public String getEditableMailTemplate() throws IOException {
        final Resource templateResource = this.applicationContext.getResource("classpath:welcome-email.html");
        final InputStream inputStream = templateResource.getInputStream();
        return IOUtils.toString(inputStream, EmailContentProperties.ENCODE_UTF_8.getValue());
    }

    
    
}
