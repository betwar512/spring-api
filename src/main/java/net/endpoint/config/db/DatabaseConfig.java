package net.endpoint.config.db;

import javax.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;


@Configuration
@EntityScan({"net.endpoint.main.model" ,"net.endpoint.account.model" , "net.endpoint.emailtemplate.model" ,
	"net.endpoint.institute.model" })
public class DatabaseConfig {


    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
        HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
    
        fact.setEntityManagerFactory(emf);
        return fact;
    }
	
}
