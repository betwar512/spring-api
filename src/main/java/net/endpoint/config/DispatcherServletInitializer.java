package net.endpoint.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;


public class DispatcherServletInitializer  extends AuthorizationServerConfigurerAdapter implements WebApplicationInitializer {
//
	@Override
    public void onStartup(ServletContext servletContext) {      
      XmlWebApplicationContext appContext = new XmlWebApplicationContext();
   
      appContext.setConfigLocation("classpath:spring-config.xml");
      //Adding the listener for the rootContext
      DelegatingFilterProxy filter2 = new DelegatingFilterProxy("CORSFilter");
      servletContext.addFilter("CORSFilter",filter2)
      .addMappingForUrlPatterns(null, false, "/*");
      servletContext.addFilter("securityFilter",new DelegatingFilterProxy("springSecurityFilterChain"))
                   .addMappingForUrlPatterns(null, false, "/*");

   //  .addMappingForUrlPatterns(null, false, "/api");
     servletContext.addListener(new ContextLoaderListener(appContext));
     //Dispatcher
      ServletRegistration.Dynamic dispatcher =
    		  servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
      dispatcher.setLoadOnStartup(1);
      dispatcher.addMapping("/");
          
    }
    
//	@Bean
//	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(
//	            dispatcherServlet);
//	    registration.addUrlMappings("/whatever/*", "/whatever2/*");
//	    return registration;
//	}
	
 }