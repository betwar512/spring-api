package net.endpoint.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("classpath:spring-config.xml")
@ComponentScan({ "net.endpoint.controller" })
public class SpringConfig {

	
	
	
	
}
