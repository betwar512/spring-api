package net.endpoint.emailtemplate.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.endpoint.emailtemplate.model.EmailTemplate;
import net.endpoint.main.dao.BaseDao;
@Repository
public class EmailTemplateDaoImpl extends BaseDao implements EmailTemplateDao {

	@Override
	public EmailTemplate loadById(long id) {
		return this.getSession().load(EmailTemplate.class, id);
	}

	@Override
	public List<EmailTemplate> findByName(String name) {
	@SuppressWarnings("unchecked")
	List<EmailTemplate> list = this.getSession()
								   .createQuery("from EmailTemplate where name=:name")
								   .setParameter("name", name)
								   .list();
	
	    	return list != null  ? list : new ArrayList<>();
     	}

	@Override
	public List<EmailTemplate> getAll() {
		@SuppressWarnings("unchecked")
		List<EmailTemplate> list = this.getSession()
				.createQuery("from EmailTemplate emailTemplate")
				.list();
		return list!= null ? list : new ArrayList<>();
	}

	
	
}
