package net.endpoint.config.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DatabaseConfig {

	
	@Value("${jdbc.driverClassName}")   private String driverClassName;
	@Value("${jdbc.url}")               private String url;
	@Value("${jdbc.username}")          private String username;
	@Value("${jdbc.password}")          private String password;
	    
	@Value("${hibernate.dialect}")      private String hibernateDialect;
	@Value("${hibernate.show_sql}")     private String hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}") private String hibernateHbm2ddlAuto;
	        
	@Bean()    
	 public DataSource getDataSource(){
	        DriverManagerDataSource ds = new DriverManagerDataSource();        
	        ds.setDriverClassName(driverClassName);
	        ds.setUrl(url);
	        ds.setUsername(username);
	        ds.setPassword(password);        
	 return ds;
	 }
	    
	 @Bean
	 public LocalSessionFactoryBean sessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(getDataSource());
	      sessionFactory.setPackagesToScan(new String[]{"net.endpoint.model"});
	      sessionFactory.setHibernateProperties(getHibernateProperties());
	 
	   return sessionFactory;
	 }


	@Bean
	public Properties getHibernateProperties(){
	  Properties properties = new Properties();
	             properties.put("hibernate.dialect", hibernateDialect);
	             properties.put("hibernate.show_sql", hibernateShowSql);
	             properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
	        
	  return properties;
   }
    
}
