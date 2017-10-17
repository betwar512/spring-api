package net.endpoint.emailtemplate.dao;

import java.util.List;

import net.endpoint.emailtemplate.model.EmailTemplate;

public interface EmailTemplateDao {

	public List<EmailTemplate> getAll();
	public EmailTemplate loadById(long id);
	public List<EmailTemplate> findByName(String name);
	
	
	
}
