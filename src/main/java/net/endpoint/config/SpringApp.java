package net.endpoint.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@SpringBootApplication
@ComponentScan({ "net.endpoint" })
public class SpringApp {

	
	public static void main(String[] args){
       SpringApplication.run(SpringApp.class, args);
	}
	
  @Bean
  public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
	        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	        ppc.setLocation(new ClassPathResource("application.properties"));
	        ppc.setIgnoreUnresolvablePlaceholders(true);
	        return ppc;
	    }
  
  @Bean
  public WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurerAdapter() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
              registry.addMapping("/**");
          }
      };
  }
  
	
 }
