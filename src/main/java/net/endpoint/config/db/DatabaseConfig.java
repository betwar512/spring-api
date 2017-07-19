package net.endpoint.config.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
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
	@Autowired        
    DataSource dataSource;
//	@Bean()    
//	 public DataSource getDataSource(){
//	        DriverManagerDataSource ds = new DriverManagerDataSource();        
//	        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//	        ds.setUrl("jdbc:mysql://52.63.208.154:3306/mailserver");
//	        ds.setUsername("root");
//	        ds.setPassword("Solmaz662M");        
//	 return ds;
//	 }
	    
	 @Bean
	 public SessionFactory sessionFactory() {
	      LocalSessionFactoryBean localFactory = new LocalSessionFactoryBean();
	      localFactory.setDataSource(dataSource);
	      localFactory.setPackagesToScan("net.endpoint.model");
	      localFactory.setHibernateProperties(getHibernateProperties());
	 
	   return localFactory.getObject();
	 }

//	 @Bean
//		public HibernateTransactionManager hibernateTransactionManager(){
//		    return new HibernateTransactionManager(sessionFactory());
//		}
	 

	@Bean
	public Properties getHibernateProperties(){
	  Properties properties = new Properties();
	             properties.put("hibernate.dialect", hibernateDialect);
	             properties.put("hibernate.show_sql", hibernateShowSql);
	             properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
	        
	  return properties;
   }
    
}
